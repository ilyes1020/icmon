package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICBall extends ICMonItem{

    private Pokemon insidePokemon;

    /**
     * Empty ICBall
     * @param area
     * @param position
     */
    public ICBall(Area area, DiscreteCoordinates position) {
        super(area, position, "items/icball");
    }

    /**
     * ICBall containing a Pokemon
     * @param area
     * @param position
     * @param insidePokemon
     */
    public ICBall(Area area, DiscreteCoordinates position, Pokemon insidePokemon) {
        this(area, position);
        this.insidePokemon=insidePokemon;
    }
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * @return The captured Pokemon
     */
    public Pokemon getInsidePokemon() {
        return insidePokemon;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

}
