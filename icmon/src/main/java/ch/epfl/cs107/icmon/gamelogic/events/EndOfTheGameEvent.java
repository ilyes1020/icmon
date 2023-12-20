package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;

/**
 * Event marking the end of the game
 */
public class EndOfTheGameEvent extends ICMonEvent{

    /**
     * Constructor of an EndOfTheGameEvent
     *
     * @param player The player
     */
    public EndOfTheGameEvent(ICMonPlayer player) {
        super(player);
        onStart(new LogAction("the second event has started !"));
    }

    /**
     * Interaction with an ICShopAssistant during the event
     *
     * @param assistant           The ICShopAssistant to interact with
     * @param isCellInteraction   Indicates if it's a contact interaction
     */
    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        System.out.println("I heard that you were able to implement this step successfully. Congrats !");
        player.openDialog("end_of_game_event_interaction_with_icshopassistant");
    }
}
