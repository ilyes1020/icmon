package ch.epfl.cs107.icmon.actor;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

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
    private ArrayList<DiscreteCoordinates> additionalCoords = new ArrayList<>();
    /**
     * Default AreaEntity constructor
     *
     * @param currentArea        (Area): Owner area. Not null
     * @param mainCoords    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area currentArea, String arrivalAreaName, DiscreteCoordinates arrivalAreaPosition, DiscreteCoordinates mainCoords) {
        super(currentArea, Orientation.UP, mainCoords);
        this.mainCoords = mainCoords;
        this.arrivalAreaName = arrivalAreaName;
        this.arrivalAreaPosition = arrivalAreaPosition;
    }
    public Door(Area currentArea, String arrivalAreaName, DiscreteCoordinates arrivalAreaPosition, DiscreteCoordinates mainCoords, DiscreteCoordinates... additionalCoords) {
        this(currentArea, arrivalAreaName, arrivalAreaPosition, mainCoords);
        this.additionalCoords.addAll(Arrays.asList(additionalCoords));
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new ArrayList<>(additionalCoords);
        currentCells.add(getCurrentMainCellCoordinates());
        return currentCells;
    }

    public String getArrivalAreaName(){
        return arrivalAreaName;
    }
    public DiscreteCoordinates getArrivalAreaPosition(){
        return arrivalAreaPosition;
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
