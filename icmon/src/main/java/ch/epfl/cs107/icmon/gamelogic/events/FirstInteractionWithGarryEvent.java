package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.*;

/**
 * Event for the first interaction with Garry in the ICMon game.
 */
public class FirstInteractionWithGarryEvent extends ICMonEvent{

    private Garry garry;
    private boolean garryHasToLeave = false;
    private boolean talkedToGarry = false;
    private StartFightAction fightAction;
    //boolean to avoid problem when we perform too many times
    private boolean afterWinQuestDisplayed = false;
    /**
     * Constructor of a FirstInteractionWithGarryEvent
     *
     * @param player The player
     */
    public FirstInteractionWithGarryEvent(ICMonPlayer player) {
        super(player);
        onStart(new DisplayQuestInfoAction(player, "Fight Garry !", "actors/garry_icon"));
    }

    /**
     * Updates the event, allow chronology inside the event based on player's interactions with Garry
     * Completes the event when we win against Garry
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(!player.isDialog()){
            if (talkedToGarry && fightAction != null) {
                fightAction.perform();
                talkedToGarry = false;
            } else if (garryHasToLeave) {
                complete();
            }
        //display the quest after beating Garry, perform once
        }if (garry != null && !garry.hasPokemonLeft() && !afterWinQuestDisplayed){
            player.setQuestInfoGraphic("Talk to Garry", "actors/garry_icon");
            afterWinQuestDisplayed = true;
        }
    }

    /**
     * Interaction with Garry during the event.
     * Manage dialogs
     * @param garry             Garry
     * @param isCellInteraction Indicates if it's a contact interaction
     */
    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        this.garry = garry;
        if (garry.hasPokemonLeft()){
            player.openDialog("first_interaction_with_garry");
            fightAction = new StartFightAction(player, garry);
            talkedToGarry = true;
        }else{
            player.openDialog("interaction_with_garry_defeated");
            garryHasToLeave = true;
            onComplete(new LeaveAreaAction(garry));
        }
    }
}
