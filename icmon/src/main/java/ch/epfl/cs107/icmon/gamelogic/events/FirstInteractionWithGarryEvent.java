package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

/**
 * Event for the first interaction with Garry in the ICMon game.
 */
public class FirstInteractionWithGarryEvent extends ICMonEvent{

    /**
     * Constructor of a FirstInteractionWithGarryEvent
     *
     * @param player The player
     */
    public FirstInteractionWithGarryEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    /**
     * Interaction with Garry during the event.
     *
     * @param garry             Garry
     * @param isCellInteraction Indicates if it's a contact interaction
     */
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        player.fight(garry);
        complete();
    }
}
