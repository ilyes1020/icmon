package ch.epfl.cs107.icmon.actor.items;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        1/12/2023
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class ICBall extends ICMonItem{
    public ICBall(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
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

    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
