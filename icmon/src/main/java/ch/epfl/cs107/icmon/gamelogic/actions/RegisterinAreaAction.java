package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

/**
 * Action to register an actor in an area
 */
public class RegisterinAreaAction implements Action{
    Area area;
    Actor actor;
    public RegisterinAreaAction(Area area, Actor actor){
        this.area = area;
        this.actor = actor;
    }
    @Override
    public void perform() {
        area.registerActor(actor);
    }
}
