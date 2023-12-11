package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class LeaveAreaAction implements Action{
    Area area;
    Actor actor;
    public LeaveAreaAction(Area area, Actor actor){
        this.area=area;
        this.actor=actor;
    }


    @Override
    public void perform() {
        area.unregisterActor(actor);
    }
}
