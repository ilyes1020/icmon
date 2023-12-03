package ch.epfl.cs107.icmon.actor.items;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        1/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICBall extends ICMonItem{


    public ICBall(Area area, DiscreteCoordinates position, String spriteName) {
        super(area, position, spriteName);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    //no need to override isCellInteractable(), same as super
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

    private class ICBallHandler implements ICMonInteractionVisitor{

    }
}
