package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;

/**
 * Action to suspend the event
 */
public class SuspendEventAction implements Action{

    ICMon.ICMonGameState gameState;
    ICMonEvent event;

    public SuspendEventAction(ICMonEvent event,ICMon.ICMonGameState gameState){
        this.gameState = gameState;
        this.event = event;
    }

    @Override
    public void perform() {
        if (event instanceof PauseMenuEvent){
            gameState.pauseTheGame((PauseMenuEvent) event);
        }
    }
}
