package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;

/**
 * Action to suspend the event
 */
public class SuspendEventAction implements Action{

    ICMon.ICMonEventManager eventManager;
    ICMonEvent event;

    public SuspendEventAction(ICMonEvent event,ICMon.ICMonEventManager eventManager){
        this.eventManager = eventManager;
        this.event = event;
    }

    @Override
    public void perform() {
        if (event instanceof PauseMenuEvent){
            eventManager.pauseTheGame((PauseMenuEvent) event);
        }
    }
}
