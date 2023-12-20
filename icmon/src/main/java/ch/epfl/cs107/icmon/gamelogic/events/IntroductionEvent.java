package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.OpenDialogAction;

public class IntroductionEvent extends ICMonEvent{

    /**
     * Constructor of an IntroductionEvent
     *
     * @param player The player
     */
    public IntroductionEvent(ICMonPlayer player) {
        super(player);
        onStart(new OpenDialogAction(player,"welcome_to_icmon"));
    }
    /**
     * Updates the event over time and check if the player has closed the dialog, and complete the event if true
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(!player.isDialog()){
            complete();
        }
    }
}
