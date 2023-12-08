package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        8/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action{

    ICMonEvent event;

    public StartEventAction(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void perform() {
        event.start();
    }
}
