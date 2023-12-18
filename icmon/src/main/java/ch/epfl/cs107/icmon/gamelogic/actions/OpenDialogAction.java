package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

public class OpenDialogAction implements Action{
    private ICMonPlayer player;
    private String dialogKey;
    public OpenDialogAction(ICMonPlayer player, String dialogKey){
        this.player = player;
        this.dialogKey=dialogKey;
    }
    @Override
    public void perform() {
        player.openDialog(dialogKey);
    }
}
