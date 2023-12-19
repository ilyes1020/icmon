package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

import java.sql.SQLOutput;

public class FirstInteractionWithGarryEvent extends ICMonEvent{

    private boolean garryIsDead = false;
    private Garry garry;
    public FirstInteractionWithGarryEvent(ICMonPlayer player,Garry garry) {
        super(player);
        this.garry = garry;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (garry.hasToLeaveArea()){
            complete();
        }
    }

    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        System.out.println("interaction avec garry");
        player.fight(garry);
        System.out.println(garry.hasToLeaveArea());
    }
}
