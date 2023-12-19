package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;

import java.sql.SQLOutput;

public class FirstInteractionWithGarryEvent extends ICMonEvent{

    private boolean hasInteracted;
    public FirstInteractionWithGarryEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        player.fight(garry);
        complete();
    }
}
