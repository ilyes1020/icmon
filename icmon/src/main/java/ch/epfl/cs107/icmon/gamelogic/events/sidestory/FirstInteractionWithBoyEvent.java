package ch.epfl.cs107.icmon.gamelogic.events.sidestory;

import ch.epfl.cs107.icmon.actor.npc.ICBoy;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class FirstInteractionWithBoyEvent extends ICMonEvent {

    private ICBoy boy;

    private boolean isCreated;

    private ICMonArea area;

    /**
     * Constructor of a FirstInteractionWithBoyEvent
     *
     * @param player The player
     */
    public FirstInteractionWithBoyEvent(ICMonPlayer player, ICBoy boy, ICMonArea area) {
        super(player);
        this.boy = boy;
        this.area = area;
    }


    @Override
    public void update(float deltaTime) {
        // distance between the player and the boy
        float distance = DiscreteCoordinates.distanceBetween(player.getCurrentCells().get(0), boy.getCurrentCells().get(0));

        if (area.isStarted() && !isCreated) {
            isCreated = true;
            area.registerActor(boy);
        }

        if (distance<=8){
            area.setViewCandidate(boy);
            player.openDialog("waiting");
            boy.setTarget(player.getCurrentCells().get(0));
            if (distance==1){
                boy.stopTargeting();
                area.setViewCandidate(player);
                player.openDialog("first_interaction_with_boy");
                complete();
            }
        }
    }
}
