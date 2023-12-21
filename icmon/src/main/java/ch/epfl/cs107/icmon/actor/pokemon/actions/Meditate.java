package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * Meditate, and increases the users Attack Damage by 2
 */
public class Meditate extends OnSelfAction{
    /**
     * Meditate action constructor
     *
     */
    public Meditate(){}
    @Override
    public String name() {
        return "Meditate";
    }
    @Override
    public boolean doAction(Pokemon target) {
        if(target != null) {
            int attackIncrease = 2;
            target.modifieAttack(attackIncrease);
            return true;
        }
        return false;
    }
}
