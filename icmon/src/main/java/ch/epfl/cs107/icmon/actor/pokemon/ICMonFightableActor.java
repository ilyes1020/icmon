package ch.epfl.cs107.icmon.actor.pokemon;

import java.util.List;

public interface ICMonFightableActor{

    /**
     * To know if the ICMonFightableActor has Pokémon left
     * @return 'true' if the ICMonFightableActor has at least one Pokémon left, 'false' otherwise.
     */
    boolean hasPokemonLeft();
    /**
     * To know if the ICMonFightableActor has to leave the game anyway when the fight is finished,
     * if not, the specific event will manage when he will leave
     * @return 'true' if the ICMonFightableActor has to leave the area, 'false' otherwise.
     */
    boolean hasToLeaveAfterFight();

    /**
     * Retrieves a list of Pokemons owned or associated with the ICMonFightableActor.
     *
     * @return A list containing Pokemon objects owned or associated with the ICMonFightableActor.
     */
    public List<Pokemon> getPokemons();
}
