
# ICMon

## Overview

The ICMon game is an RPG that involves various elements in a grid-based gameplay. These elements can interact with each other, and these interactions have consequences for the game's progression.

## Run the game

Quick commands to build and run the game from the repository root:

```bash
mvn clean install
cd icmon
mvn exec:java
```

## Game Instructions

### Controls
-   WASD: Movement
-   E: Interaction with the environment.
-   Enter: Start the game + confirm certain menu selections.
-   Space: Skip dialogues
-   F: In battle, restores Pokemon's health points.
-   G: Remove Quest Info from the screen.

### ICMon Main Story Solution

The trainer appears in their house and begins their adventure, similar to a classic Pokemon game.

#### How to Progress in the Story

-   First, talk to Professor Oak in his Lab, resulting in acquiring a Pokemon and an introduction to the area.
-   It's now time to battle Gary. Return to the starting house to interact with Gary.
-   After the battle, talk to him again.
-   Next, talk to an assistant (found in the store and on the water below the town).
-   Bring her the SuperBall.
-   Congratulations, you have completed the game;)

### ICMon Secondary Story Solution

After interacting with Professor Oak and leaving the Lab, a little boy runs up to you.

#### How to Progress in the Secondary Story
-   After the interaction with the little boy, go to the upper-right part of the town (above the water area) to pick up a key.
-   With the key, open the door of the house near the lower water area.
-   Interact with the bully and engage in a fight.
-   He will then return the Pokemon he stole.
-   Go back to the little boy to return it to him.
-   Congratulations, you have completed the secondary story ;)

### EasterEgg Solution

Activated during the CollectItemEvent.

#### How to Activate the EasterEgg
-   When you interact with an assistant 10 times, a battle with her starts.
-   Assistants have a MewTwo (which is too strong for you).
-   You now understand not to bother them ;)

### Combat Mechanics

-   Battles are turn-based (you take the first action).
-   "Run Away" allows you to escape the battle.
-   "Attack" reduces the enemy's health points.
-   "Meditate" increases damage dealt by 2 (owned by Bulbasaur).
-   "Roost" heals 50% of the Pokemon's HP (owned by Latios).
-   "Growl" reduces the enemy's damage dealt by 1 (owned by Nidoqueen).

## Disclaimer

This project is part of the EPFL course CS-107. The `game-engine` used in this repository is provided by the course and does not belong to the project authors.