package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;

/**
 * Represents a gameplay message for suspending an event
 * When processed, it suspends the specified event and triggers additional actions based on the event type
 */
public class SuspendWithEventMessage extends GamePlayMessage{
    private ICMonEvent event;
    private ICMon.ICMonGameState gameState;

    /**
     * Constructor for creating a SuspendWithEventMessage
     *
     * @param event     The event to be suspended
     * @param gameState The game state which can send the message
     */
    public SuspendWithEventMessage(ICMonEvent event, ICMon.ICMonGameState gameState){
        this.event = event;
        this.gameState=gameState;
    }

    /**
     * Prints on the console the reason of the suspension when an event is suspended
     * Suspends the event on its start, resumes it when it is completed
     */
    @Override
    public void process() {
        if (event instanceof PauseMenuEvent){
            System.out.print("suspension des événements en cours");
            event.onStart(new SuspendEventAction(event,gameState));
            if(event instanceof PokemonFightEvent){
                event.onComplete(new ResumeEventAction(event,gameState));
                System.out.println(" à cause d’un événement combat");
            } else if (event instanceof PokemonSelectionEvent) {
                System.out.println(" à cause d'un événement séléction");
            }
        }
        event.start();
    }
}
