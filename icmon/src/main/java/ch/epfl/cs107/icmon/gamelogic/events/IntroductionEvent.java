package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.OpenDialogAction;

public class IntroductionEvent extends ICMonEvent{
    public IntroductionEvent(ICMonPlayer player) {
        super(player);
        player.openDialog("welcome_to_icmon");
//        onStart(new OpenDialogAction(player,"welcome_to_icmon"));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        System.out.println(player.isDialog());
        if(!player.isDialog()){
            complete();
        }
    }
}
