package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.play.engine.PauseMenu;

public interface PauseMenuEvent { //just to know which events are meant to pause to game
    public PauseMenu getPauseMenu();
}
