package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

/**
 * Action to display the current quest
 */
public class DisplayQuestInfoAction implements Action{
    private ICMonPlayer player;
    String iconName, text;
    public DisplayQuestInfoAction(ICMonPlayer player, String text, String iconName){
        this.player = player;
        this.text = text;
        this.iconName = iconName;
    }
    @Override
    public void perform() {
        player.setQuestInfoGraphic(text, iconName);
        player.displayQuestInfo();
    }
}
