package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Garry extends NPCActor implements ICMonFightableActor {

    private List<Pokemon> pokemons = new ArrayList<>();

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */


    public Garry(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "actors/garry");
        pokemons.add(new Nidoqueen(getOwnerArea(),new DiscreteCoordinates(0,0)));
    }
    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }
    public Garry(Area area, DiscreteCoordinates position){
        this(area,Orientation.DOWN,position);
    }

    public boolean hasPokemon(){
        return !pokemons.isEmpty();
    }

    @Override
    public boolean hasToLeaveArea() {
        for (Pokemon pokemon:pokemons){
            if (!pokemon.isDead()){
                return false;
            }
        }
        return true;
    }

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }
}
