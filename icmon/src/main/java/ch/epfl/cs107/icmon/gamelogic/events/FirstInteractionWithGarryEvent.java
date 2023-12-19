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
    public FirstInteractionWithGarryEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        System.out.println("interaction avec garry");
        player.fight(garry);
        System.out.println(garry.hasToLeaveArea());
    }
}
