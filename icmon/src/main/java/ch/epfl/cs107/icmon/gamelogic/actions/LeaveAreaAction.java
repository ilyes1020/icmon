package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonActor;

/**
 * Action to make an actor leave area
 */
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
