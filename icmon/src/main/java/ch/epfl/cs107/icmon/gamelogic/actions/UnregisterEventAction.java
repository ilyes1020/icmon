package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action to unregister an event from the game
 */
public class UnregisterEventAction implements Action{
    private ICMonEvent event;
    private ICMon.ICMonEventManager eventManager;

    public UnregisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager){
        this.event=event;
        this.eventManager=eventManager;
    }
    @Override
    public void perform() {
        eventManager.toEventsToUnRegister(event);
    }
}
