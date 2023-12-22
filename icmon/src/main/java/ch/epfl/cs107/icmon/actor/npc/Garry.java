package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Garry extends NPCActor implements ICMonFightableActor {

    //garry has to stay after the fight until the event's end
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
    public Garry(Area area, DiscreteCoordinates position){
        this(area,Orientation.DOWN,position);
    }
    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }
    @Override
    public boolean hasPokemonLeft() {
        for (Pokemon pokemon:pokemons){
            if (!pokemon.isDead()){
                return true;
            }
        }
        return false;
    }
    public boolean hasToLeaveAfterFight(){
        return false;
    }

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }
}
