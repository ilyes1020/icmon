package ch.epfl.cs107.icmon.actor.pokemon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;

public class Nidoqueen extends Pokemon{
    private ArrayList<ICMonFightAction> actions;
    private PokemonProperties stats = new PokemonProperties();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area            (Area): Owner area. Not null
     * @param position        (Coordinate): Initial position of the entity. Not null
     */
    public Nidoqueen(Area area, DiscreteCoordinates position) {
        super(area, position, "nidoqueen", 2, 15);
        actions = new ArrayList<>();
        actions.add(new RunAway());
        actions.add(new Attack(stats.damage()));
    }

    @Override
    public ArrayList<ICMonFightAction> getActions() {
        return actions;
    }
}
