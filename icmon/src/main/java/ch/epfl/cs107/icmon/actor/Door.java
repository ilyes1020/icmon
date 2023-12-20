package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Door extends AreaEntity {

    private String arrivalAreaName;
    private final DiscreteCoordinates arrivalAreaPosition;
    private final DiscreteCoordinates mainCoords;
    private List<DiscreteCoordinates> additionalCoords = new ArrayList<>();
    /**
     * Default Door constructor.
     *
     * @param currentArea           The owner area. Not null.
     * @param arrivalAreaName       The name of the arrival area. Not null.
     * @param arrivalAreaPosition   The position in the arrival area. Not null.
     * @param mainCoords            The initial position of the entity in the area. Not null.
     */
    public Door(Area currentArea, String arrivalAreaName, DiscreteCoordinates arrivalAreaPosition, DiscreteCoordinates mainCoords) {
        super(currentArea, Orientation.UP, mainCoords);
        this.mainCoords = mainCoords;
        this.arrivalAreaName = arrivalAreaName;
        this.arrivalAreaPosition = arrivalAreaPosition;
    }

    /**
     * Door constructor with additional coordinates.
     *
     * @param currentArea           The owner area. Not null.
     * @param arrivalAreaName       The name of the arrival area. Not null.
     * @param arrivalAreaPosition   The position in the arrival area. Not null.
     * @param mainCoords            The initial position of the entity in the area. Not null.
     * @param additionalCoords      Additional coordinates of the door. May be empty.
     */
    public Door(Area currentArea, String arrivalAreaName, DiscreteCoordinates arrivalAreaPosition, DiscreteCoordinates mainCoords, DiscreteCoordinates... additionalCoords) {
        this(currentArea, arrivalAreaName, arrivalAreaPosition, mainCoords);
        this.additionalCoords.addAll(Arrays.asList(additionalCoords));
    }

    /**
     * Retrieves the current cells occupied by the door, including additional coordinates.
     *
     * @return A list of coordinates representing the current cells of the door.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new ArrayList<>(additionalCoords);
        currentCells.add(getCurrentMainCellCoordinates());
        return currentCells;
    }

    /**
     * Retrieves the name of the arrival area associated with the door.
     *
     * @return The name of the arrival area.
     */
    public String getArrivalAreaName(){
        return arrivalAreaName;
    }

    /**
     * Retrieves the position (DiscreteCoordinates) in the arrival area associated with the door.
     *
     * @return The position in the arrival area as discrete coordinates.
     */
    public DiscreteCoordinates getArrivalAreaPosition(){
        return new DiscreteCoordinates(arrivalAreaPosition.x, arrivalAreaPosition.y);
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }


    @Override
    public void draw(Canvas canvas) {
    }
}
