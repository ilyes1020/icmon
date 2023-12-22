package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICBerry;
import ch.epfl.cs107.icmon.actor.items.ICKey;
import ch.epfl.cs107.icmon.actor.npc.*;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
    }
    default void interactWith(ICMonPlayer player, boolean isCellInteraction) {
    }
    default void interactWith(ICBoy boy, boolean isCellInteraction){
    }
    default void interactWith(ICBall ball, boolean isCellInteraction) {
    }
    default void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
    }
    default void interactWith(Door door, boolean isCellInteraction){
    }
    default void interactWith(Pokemon pokemon,boolean isCellInteraction){
    }
    default void interactWith(ProfOak profOak, boolean isCellInteraction){
    }
    default void interactWith(Garry garry, boolean isCellInteraction){
    }
    default void interactWith(ICBerry berry, boolean isCellInteraction){
    }
    default void interactWith(ICKey key, boolean isCellInteraction){
    }
    default void interactWith(ICBully bully, boolean isCellInteraction){
    }
}
