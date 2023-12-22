package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;

public class StartFightAction implements Action{

    ICMonPlayer player;
    ICMonFightableActor opponent;

    public StartFightAction(ICMonPlayer player, ICMonFightableActor opponent){
        this.player = player;
        this.opponent = opponent;
    }
    @Override
    public void perform() {
        player.fight(opponent);
    }
}
