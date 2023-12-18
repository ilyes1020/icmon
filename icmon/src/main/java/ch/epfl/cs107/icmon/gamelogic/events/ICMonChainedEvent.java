package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICMonChainedEvent extends ICMonEvent{

    private ICMonEvent firstEvent;
    private List<ICMonEvent> chain = new ArrayList<>();
    public ICMonChainedEvent(ICMonPlayer player, ICMonEvent firstEvent,ICMonEvent... chain) {
        super(player);
        this.firstEvent=firstEvent;
        this.chain.addAll(Arrays.asList(chain)); //à revoir
        onStart(new StartEventAction(firstEvent));
        firstEvent.onComplete(new StartEventAction(this.chain.get(0)));

        for(int i = 0; i < chain.length - 1 ; i++){
            this.chain.get(i).onComplete(new StartEventAction(this.chain.get(i+1)));
        }

        this.chain.get(chain.length-1).onComplete(new CompleteEventAction(this));
    }
}
