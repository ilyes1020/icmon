package ch.epfl.cs107.icmon.message;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PauseMenuEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;

public class SuspendWithEvent extends GamePlayMessage{
    private ICMonEvent event;
    private ICMon.ICMonGameState gameState;
    public SuspendWithEvent(ICMonEvent event, ICMon.ICMonGameState gameState){
        this.event = event;
        this.gameState=gameState;
    }
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
