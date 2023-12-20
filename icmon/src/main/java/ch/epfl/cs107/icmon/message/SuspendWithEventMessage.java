package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;

/**
 * Represents a gameplay message for suspending an event
 * When processed, it suspends the specified event and triggers additional actions based on the event type
 */
public class SuspendWithEventMessage extends GamePlayMessage{
    private ICMonEvent event;
    private ICMon.ICMonEventManager eventManager;

    /**
     * Constructor for creating a SuspendWithEventMessage
     *
     * @param event        The event to be suspended
     * @param eventManager The event manager of the game
     */
    public SuspendWithEventMessage(ICMonEvent event, ICMon.ICMonEventManager eventManager){
        this.event = event;
        this.eventManager=eventManager;
    }

    /**
     * Prints on the console the reason of the suspension when an event is suspended
     * Suspends the event on its start, resumes it when it is completed
     */
    @Override
    public void process() {
        if (event instanceof PauseMenuEvent) {
            ((PauseMenuEvent) event).pauseMessage();
            event.start();
        }
    }
}
