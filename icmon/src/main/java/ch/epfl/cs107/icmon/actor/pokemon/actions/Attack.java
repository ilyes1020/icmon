package ch.epfl.cs107.icmon.actor.pokemon.actions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

public class Attack implements ICMonFightAction {

    private int attackDamage;

    public Attack(int attackDamage){
        this.attackDamage = attackDamage;
    };
    @Override
    public String name() {
        return "Attack";
    }
    @Override
    public boolean doAction(Pokemon target) {
        System.out.println("did attack"); //pour le debuggage
        target.receiveDamage(attackDamage);
        return true;
    }
}
