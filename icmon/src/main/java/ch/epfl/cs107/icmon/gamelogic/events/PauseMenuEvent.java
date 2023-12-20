package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.play.engine.PauseMenu;

public interface PauseMenuEvent{ //just to know which events are meant to pause to game

    /**
     * Getter for the pause menu
     *
     * @return The pause menu
     */
    PauseMenu getPauseMenu();
}
