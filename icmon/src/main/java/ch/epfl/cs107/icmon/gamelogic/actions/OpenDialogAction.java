package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

/**
 * Action to open a dialog
 */
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
