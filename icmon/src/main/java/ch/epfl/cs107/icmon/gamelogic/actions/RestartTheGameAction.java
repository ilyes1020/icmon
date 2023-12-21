package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class RestartTheGameAction implements Action{
    private ICMon.ICMonEventManager eventManager;
    public RestartTheGameAction(ICMon.ICMonEventManager eventManager){
        this.eventManager = eventManager;
    }
    @Override
    public void perform() {
        eventManager.restartTheGame();
    }
}
