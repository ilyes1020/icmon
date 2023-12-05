package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        5/12/2023
 */

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterinAreaAction implements Action{

    Area area;
    Actor actor;
    public RegisterinAreaAction(Area area, Actor actor){
        this.area=area;
        this.actor=actor;
    }


    @Override
    public void perform() {
        area.registerActor(actor);
    }
}
