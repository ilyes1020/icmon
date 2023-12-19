package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.gamelogic.actions.AddPokemonToPlayerAction;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class FirstInteractionWithProfOakEvent extends ICMonEvent{
    private boolean interactedWithOak;
    public FirstInteractionWithProfOakEvent(ICMonPlayer player) {
        super(player);
        onComplete(new AddPokemonToPlayerAction(new Latios(new House(),new DiscreteCoordinates(0,0)),player)); //null
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    public void interactWith(ProfOak profOak, boolean isCellInteraction){
        player.openDialog("first_interaction_with_prof_oak");
        complete();
    }

    public void interactWith(ICShopAssistant assistant,boolean isCellInteraction){
        player.openDialog("first_interaction_with_oak_event_icshopassistant");
    }

}
