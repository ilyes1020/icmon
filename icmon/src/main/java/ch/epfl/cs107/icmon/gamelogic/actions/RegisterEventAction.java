package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

/**
 * Action to register an event to the game
 */
public class RegisterEventAction implements Action{

    private ICMon.ICMonEventManager eventManager;

    private ICMonEvent event;

    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager){
        this.event = event;
        this.eventManager = eventManager;
    }
    @Override
    public void perform() {
        eventManager.toEventsToRegister(event);
    }
}
