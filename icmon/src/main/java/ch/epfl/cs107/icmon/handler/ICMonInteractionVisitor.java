package ch.epfl.cs107.icmon.handler;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        2/02/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
    }

    default void interactWith(ICMonPlayer player, boolean isCellInteraction) {
    }

    default void interactWith(ICBall ball, boolean isCellInteraction) {
    }
    default void interactWith(ICShopAssistant assistant , boolean isCellInteraction){

    }

    default void interactWith(Door door, boolean isCellInteraction){

    }
}
