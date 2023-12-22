package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Mewtwo;
import ch.epfl.cs107.icmon.actor.pokemon.Nidoqueen;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class ICShopAssistant extends NPCActor implements ICMonFightableActor {

    // Easter Egg
    private List<Pokemon> pokemons;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */

    public ICShopAssistant(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "actors/assistant");
        pokemons= new ArrayList<>();
        pokemons.add(new Mewtwo(getOwnerArea(),new DiscreteCoordinates(0,0)));
    }
    public ICShopAssistant(Area area, DiscreteCoordinates position) {
        this(area, Orientation.DOWN, position);
    }

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }

    @Override
    public boolean hasPokemonLeft() {
        return true;
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
