package ch.epfl.cs107.icmon.gamelogic.events.sidestory;

import ch.epfl.cs107.icmon.actor.npc.ICBoy;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

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
        player.addPokemon(new Latios(new Town(),new DiscreteCoordinates(0,0)));
        complete();
    }
}
