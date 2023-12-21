package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICBerry extends ICMonItem{
    private static boolean discovered = false;

    public final static int HEALING_VALUE = 5;
    public ICBerry(Area area, DiscreteCoordinates position) {
        super(area, position, "items/icberry");
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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

    public static boolean isDiscovered(){
        return discovered;
    }
    public static void setDiscovered(boolean isDiscovered){
        discovered = isDiscovered;
    }
}

