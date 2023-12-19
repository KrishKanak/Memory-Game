CREATE DATABASE memory_gameDB;
USE memory_gameDB;

SELECT *
FROM
--  player;
-- 	game;
activity;

/* Table Creation*/
CREATE TABLE player (
                        player_id	INT 		NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        player_name VARCHAR(10) NOT NULL,
                        birthdate 	DATE 		NOT NULL,
                        pwd 		VARCHAR(20) NOT NULL
);

CREATE TABLE game (
                      game_id 		INT 	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      game_uuid 	VARCHAR(36) NOT NULL,
                      game_date 	DATE NOT NULL,
                      score 		INT NOT NULL
);

CREATE TABLE activity (
                          activity_id 		INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          player_id 		INT NOT NULL,   -- FK
                          game_id 			INT NOT NULL,       -- FK
                          commentary 		VARCHAR(50)
);


/* FOREIGN KEY linking */
ALTER TABLE activity
    ADD CONSTRAINT fk_actvy_plr  FOREIGN KEY(player_id) REFERENCES player(player_id),
    ADD CONSTRAINT fk_actvy_game  FOREIGN KEY(game_id) REFERENCES game(game_id);


-- DATA INSERTION
INSERT INTO player(player_name, birthdate, pwd)
VALUES('root', '1996-02-03', 'password');

INSERT INTO game(game_uuid, game_date, score)
VALUES('77c38a42-f5aa-11ec-b939-0242ac120002','2022-06-25', 21);

INSERT INTO activity(player_id, game_id, commentary)
VALUES(1, 1, 'played a game');


SELECT plr.player_name, game.game_date, game.score
FROM activity actvy
         INNER JOIN player plr
                    ON actvy.player_id = plr.player_id
         INNER JOIN game
                    ON actvy.game_id = game.game_id
ORDER BY game.score ;