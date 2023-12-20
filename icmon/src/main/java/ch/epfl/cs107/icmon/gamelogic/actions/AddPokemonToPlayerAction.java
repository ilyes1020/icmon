package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * Action to add a pokémon to the player
 */
public class AddPokemonToPlayerAction implements Action{

    private Pokemon pokemon;

    private ICMonPlayer player;
    public AddPokemonToPlayerAction(Pokemon pokemon, ICMonPlayer player){
        this.player = player;
        this.pokemon=pokemon;
    }
    @Override
    public void perform() {
        player.addPokemon(pokemon);
    }
}
