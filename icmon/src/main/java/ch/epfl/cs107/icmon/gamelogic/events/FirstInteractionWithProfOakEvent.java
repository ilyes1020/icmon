package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.gamelogic.actions.AddPokemonToPlayerAction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Event for the first interaction with Garry in the ICMon game.
 */
public class FirstInteractionWithProfOakEvent extends ICMonEvent{
    public FirstInteractionWithProfOakEvent(ICMonPlayer player) {
        super(player);
        onComplete(new AddPokemonToPlayerAction(new Latios(new House(),new DiscreteCoordinates(0,0)),player)); //null
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    /**
     * Interaction with Professor Oak during the event
     *
     * @param profOak            The Professor Oak
     * @param isCellInteraction Indicates if it's a cell interaction
     */
    public void interactWith(ProfOak profOak, boolean isCellInteraction){
        player.openDialog("first_interaction_with_prof_oak");
        complete();
    }

    /**
     * Interaction with an ICShopAssistant during the event
     *
     * @param assistant         The ICShopAssistant to interact with
     * @param isCellInteraction Indicates if it's a contact interaction
     */
    public void interactWith(ICShopAssistant assistant,boolean isCellInteraction){
        player.openDialog("first_interaction_with_oak_event_icshopassistant");
    }

}
