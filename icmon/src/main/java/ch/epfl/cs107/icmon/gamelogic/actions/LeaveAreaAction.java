package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class LeaveAreaAction implements Action{
    ICMonActor actor;
    public LeaveAreaAction(ICMonActor actor){
        this.actor = actor;
    }
    @Override
    public void perform() {
        actor.leaveArea();
    }
}
