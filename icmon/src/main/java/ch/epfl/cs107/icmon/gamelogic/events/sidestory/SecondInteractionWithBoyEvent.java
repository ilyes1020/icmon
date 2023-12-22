package ch.epfl.cs107.icmon.gamelogic.events.sidestory;

import ch.epfl.cs107.icmon.actor.npc.ICBoy;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class SecondInteractionWithBoyEvent extends ICMonEvent {
    /**
     * Constructor of an ICMonEvent
     *
     * @param player The player
     */
    public SecondInteractionWithBoyEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void interactWith(ICBoy boy, boolean isCellInteraction) {
        player.openDialog("second_interaction_with_boy");
        complete();
    }
}
