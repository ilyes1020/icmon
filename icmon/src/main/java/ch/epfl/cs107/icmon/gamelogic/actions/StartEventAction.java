package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action to start the event
 */
public class StartEventAction implements Action{
    ICMonEvent event;

    public StartEventAction(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void perform() {
        event.start();
    }
}
