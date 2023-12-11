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
            if(event instanceof PokemonFightEvent){
                System.out.print( " à cause d’un événement combat");
            }
        }
        event.onStart(new SuspendEventAction(event,gameState));
        event.onComplete(new ResumeEventAction(event,gameState));
        event.start();
    }
}
