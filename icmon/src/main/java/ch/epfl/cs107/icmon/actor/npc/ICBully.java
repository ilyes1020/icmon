package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class ICBully extends NPCActor implements ICMonFightableActor {

    private List<Pokemon> pokemons = new ArrayList<>();
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICBully(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position, "actors/bully");
        pokemons.add(new Bulbizarre(area,new DiscreteCoordinates(0,0)));
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }

    @Override
    public boolean hasPokemonLeft() {
        return false;
    }

    @Override
    public boolean hasToLeaveAfterFight() {
        return false;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
