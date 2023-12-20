package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action to complete the event
 */
public class CompleteEventAction implements Action{

    ICMonEvent event;

    public CompleteEventAction(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void perform() {
        event.complete();
    }
}
