package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        8/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action{

    private ICMon.ICMonEventManager eventManager;

    private ICMonEvent event;

    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager eventManager){
        this.event = event;
        this.eventManager =eventManager;
    }
    @Override
    public void perform() {
        eventManager.registerEvent(event);
    }
}
