package ch.epfl.cs107.icmon.gamelogic.events.sidestory;

import ch.epfl.cs107.icmon.actor.items.ICKey;
import ch.epfl.cs107.icmon.actor.npc.ICBoy;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class FindTheKeyEvent extends ICMonEvent {
    /**
     * Constructor of an ICMonEvent
     *
     * @param player The player
     */
    public FindTheKeyEvent(ICMonPlayer player, ICMonArea area, ICKey key) {
        super(player);
        onStart(new RegisterinAreaAction(area,key));
    }

    @Override
    public void interactWith(ICKey key, boolean isCellInteraction) {
        super.interactWith(key, isCellInteraction);
        complete();
    }
}
