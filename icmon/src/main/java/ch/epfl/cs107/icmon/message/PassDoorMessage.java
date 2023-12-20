package ch.epfl.cs107.icmon.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;

/**
 * Represents a gameplay message for passing through a door
 * When processed, it switches to the arrival area defined by the door
 */
public class PassDoorMessage extends GamePlayMessage{
    private Door door;
    private ICMon.ICMonGameState gameState;

    /**
     * Constructor of a PassDoorMessage
     *
     * @param door      The door through which the player is passing
     * @param gameState The game state that switches the player's area
     */
    public PassDoorMessage(Door door, ICMon.ICMonGameState gameState){
        this.door = door;
        this.gameState = gameState;
    }

    /**
     * switches the current area to an area defined by the door
     */
    @Override
    public void process() {
        gameState.switchArea(door.getArrivalAreaName(), door.getArrivalAreaPosition());
    }
}