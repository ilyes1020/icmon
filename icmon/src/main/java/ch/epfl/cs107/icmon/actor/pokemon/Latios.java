package ch.epfl.cs107.icmon.actor.pokemon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;

public class Latios extends Pokemon{
    private ArrayList<ICMonFightAction> actions;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area            (Area): Owner area. Not null
     * @param position        (Coordinate): Initial position of the entity. Not null
     */
    public Latios(Area area, DiscreteCoordinates position) {
        super(area, position, "latios", 1, 10);
    }

    @Override
    public ArrayList<ICMonFightAction> getActions() {
        return actions;
    }
}
