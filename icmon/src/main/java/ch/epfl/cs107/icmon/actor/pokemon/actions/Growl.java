package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

/**
 * Growl fears the target, decreasing his attack damage by 1
 */
public class Growl extends OnTargetAction{
    /**
     * Growl action constructor
     *
     */
    public Growl(){}
    @Override
    public String name() {
        return "Growl";
    }
    @Override
    public boolean doAction(Pokemon target) {
        if(target != null) {
            int attackDecrease = -1;
            target.modifieAttack(attackDecrease);
            return true;
        }
        return false;
    }
}
