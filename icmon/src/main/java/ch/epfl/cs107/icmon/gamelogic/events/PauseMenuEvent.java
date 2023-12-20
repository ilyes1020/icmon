package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.play.engine.PauseMenu;

public interface PauseMenuEvent{

    /**
     * Getter for the pause menu
     *
     * @return The pause menu
     */
    PauseMenu getPauseMenu();

    void pauseMessage();

}
