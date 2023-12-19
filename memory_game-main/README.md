<h1 align="center">
  Memory Game
</h1>
This project was made with java.

## Table of Contents
- [1. Getting Started](#1-getting-started)
    - [1.1 Folder Structure](#1.1-folder-structure)
    - [1.2 Tools Required](#1.2-tools-required)
- [2. Development](#2-development)
    - [2.1 Set up the project](#21-set-up-the-project)
      - [Step 1: Install Java JDK](#step-1-install-java-jdk)
      - [Step 2: Download the project from Teams](#step-2-download-the-project-from-teams)
      - [Step 3: Run the project](#step-3-run-the-project)



# 1. Getting Started
This project was commissioned by subject teachers we made a memory game with java.

## 1.1 Folder Structure
The folder structure of the workspace contains:
```
    memory_game_team2
    ├── .idea
    ├── .vscode
        └── settings.json
    ├── assets
        └── memory_gameDB.sql
    ├── bin
        ├── auth
            ├── Login.class
            ├── MyConnection.class
            ├── Signup.class
            └── UpdateAccount.class
        ├── mechanism
            ├── Game.class
            └── Scoreboard.class
        ├── nav
            └── Navigation.class
        └── App.class 
    ├── lib
        └── mysql-connector-java-8.0.29.jar
    ├── out
    ├── src
        ├── auth
            ├── Login.java
            ├── MyConnection.java
            ├── Signup.java
            └── UpdateAccount.java
        ├── mechanism
            ├── Game.java
            └── Scoreboard.java
        ├── nav
            └── Navigation.java
        └── App.java 
    ├── memory_game_team2.iml
    └── README.MD
```
## 1.2 Tools Required
The workspace following tools to develop and run the project:
- A text editor(like [VS Code](https://code.visualstudio.com/)) or an IDE (like [IntelliJ](https://www.jetbrains.com/idea/)))
- Java Development Kit such as [Oracle JDK 15 or newer](https://www.oracle.com/java/technologies/java-se-glance.html) or [Amazon Corretto 17 or newer](https://aws.amazon.com/corretto/)

# 2. Development

## 2.1 Set up the Project
The steps to run the project:

### Step 1. Install Java JDK
You can either use Amazon Corretto or Oracle JDK. 

### Step 2. Download the project from Teams
The Project is uploaded on Microsoft Teams.

### Step 3. Run the project
Run the project in your IDE or text editor.

## Instructions

When starting the application, the player is given the option to choose between 5 options. These are:


- **play game**. The player is asked to enter his or her username and password to access the game and then play.

- **Sign up**. Before being able to play the game, the player must first create an account. This can be done using the “sign up” option. The player will be asked to enter the name, date of birth and password. If the registration is successful, the player is registered in the system and can access the game.

- **Exit game**. By choosing this option, the player/user exits the game.

- **Edit account**. The player can adjust his/her account with this option.

- **leaderboard**. This allows the 10 highest scores with associated names to be retrieved.

