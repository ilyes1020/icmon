package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import java.util.List;

/**
 * Represents a chained sequence of events in the ICMon game.
 */
public class ICMonChainedEvent extends ICMonEvent{

    private ICMonEvent firstEvent;
    private List<ICMonEvent> chain;

    /**
     * Constructor of ICMonChainedEvent
     *
     * @param player     The player
     * @param firstEvent The first event in the chain
     * @param chain      The subsequent events forming the chain
     */
    public ICMonChainedEvent(ICMonPlayer player, ICMonEvent firstEvent,ICMonEvent... chain) {
        super(player);
        this.firstEvent=firstEvent;
        this.chain = List.of(chain);
        onStart(new StartEventAction(firstEvent));
        firstEvent.onComplete(new StartEventAction(this.chain.get(0)));

        for(int i = 0; i < chain.length - 1 ; i++){
            this.chain.get(i).onComplete(new StartEventAction(this.chain.get(i+1)));}

        this.chain.get(chain.length-1).onComplete(new CompleteEventAction(this));
    }
}
