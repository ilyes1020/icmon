package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        7/12/2023
 */

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;

public class EndOfTheGameEvent extends ICMonEvent{

    public EndOfTheGameEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        System.out.println("I heard that you were able to implement this step successfully. Congrats !");
        player.openDialog("end_of_game_event_interaction_with_icshopassistant");
    }
}
