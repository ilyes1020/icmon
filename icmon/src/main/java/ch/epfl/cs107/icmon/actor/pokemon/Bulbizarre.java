package ch.epfl.cs107.icmon.actor.pokemon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Bulbizarre extends Pokemon{

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area            (Area): Owner area. Not null
     * @param position        (Coordinate): Initial position of the entity. Not null
     */
    public Bulbizarre(Area area, DiscreteCoordinates position) {
        super(area, position, "bulbizarre", 1, 10);
    }
}
