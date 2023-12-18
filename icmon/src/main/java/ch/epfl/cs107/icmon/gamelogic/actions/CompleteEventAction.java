package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class CompleteEventAction implements Action{

    ICMonEvent event;

    public CompleteEventAction(ICMonEvent event){
        this.event = event;
    }
    @Override
    public void perform() {
        event.complete();
    }
}
