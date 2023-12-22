package ch.epfl.cs107.icmon.gamelogic.events.sidestory;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.npc.ICBully;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class FirstInteractionWithBullyEvent extends ICMonEvent {

    private ICBully bully;

    private boolean hasInteracted;

    private boolean hasFought;

    /**
     * Constructor of an ICMonEvent
     *
     * @param player The player
     */
    public FirstInteractionWithBullyEvent(ICMonPlayer player) {
        super(player);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (hasInteracted) {
            if (!player.isDialog()) {
                hasInteracted=false;
                player.fight(bully);
                hasFought=true;
            }
        }
        if(hasFought){
            // distance between the player and the bully
            float distance = DiscreteCoordinates.distanceBetween(player.getCurrentCells().get(0), bully.getCurrentCells().get(0));
            if (distance >= 5) { // starts targeting after a distance of 5
                bully.setTarget(player.getCurrentCells().get(0));
            }
            if (bully.isTargeting()) { //waiting during the targeting
                player.openDialog("waiting");
            }
            if (distance == 1 && bully.isTargeting()) { //
                bully.stopTargeting();
                player.openDialog("second_interaction_with_bully");
                complete();
            }
        }
    }

    @Override
    public void interactWith(ICBully bully, boolean isCellInteraction) {
        player.openDialog("first_interaction_with_bully");
        this.bully = bully;
        hasInteracted =true;
    }
}
