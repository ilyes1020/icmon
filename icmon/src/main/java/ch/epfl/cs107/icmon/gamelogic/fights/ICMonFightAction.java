package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public interface ICMonFightAction {

    /**
     * Getter of the name of the fight action
     *
     * @return The name of the fight action
     */
    String name();

    /**
     * Performs the fight action on the target Pokémon
     *
     * @param target The Pokémon on which the action is performed
     * @return True if the action was successfully executed, false otherwise
     */
    boolean doAction(Pokemon target);

}
