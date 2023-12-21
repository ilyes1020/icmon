package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * The user heals himself 50% of his max health
 */
public class Roost extends OnSelfAction{

    /**
     * Roost action constructor
     *
     */
    public Roost(){};
    @Override
    public String name() {
        return "Roost";
    }
    @Override
    public boolean doAction(Pokemon target) {
        if(target != null) {
            target.heal((int) target.properties().maxHp()/2);
            return true;
        }
        return false;
    }
}
