package ch.epfl.cs107.icmon.actor.pokemon;

import java.util.List;

public interface ICMonFightableActor{
    /**
     * To know the condition for the ICMonFightableActor to leave the game when the fight is finished
     * @return 'true' if the ICMonFightableActor has to leave the area, 'false' otherwise.
     */
    public boolean hasToLeaveArea();

    /**
     * Retrieves a list of Pokemons owned or associated with the ICMonFightableActor.
     *
     * @return A list containing Pokemon objects owned or associated with the ICMonFightableActor.
     */
    public List<Pokemon> getPokemons();
}
