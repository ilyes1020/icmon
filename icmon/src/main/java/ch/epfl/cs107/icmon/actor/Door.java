package ch.epfl.cs107.icmon.actor;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Door extends AreaEntity {

    private String toAreaName;

    private DiscreteCoordinates toAreaPosition;

    private List<DiscreteCoordinates> additionalCoords;
    /**
     * Default AreaEntity constructor
     *
     * @param currentArea        (Area): Owner area. Not null
     * @param mainCoords    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Door(Area currentArea, String toAreaName, DiscreteCoordinates toAreaPosition, DiscreteCoordinates mainCoords) {
        super(currentArea, Orientation.UP, mainCoords);
        this.toAreaName = toAreaName;
        this.toAreaPosition = toAreaPosition;
        this.additionalCoords = new ArrayList<>();
    }
    public Door(Area currentArea, String toAreaName, DiscreteCoordinates toAreaPosition, DiscreteCoordinates mainCoords, List<DiscreteCoordinates>... additionalCoords) {
        this(currentArea, toAreaName, toAreaPosition, mainCoords);
        for (List<DiscreteCoordinates> coordsList : additionalCoords) {
            this.additionalCoords.addAll(coordsList);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new ArrayList<>(additionalCoords);
        currentCells.add(getCurrentMainCellCoordinates());
        return currentCells;
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

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
