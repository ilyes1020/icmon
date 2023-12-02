package ch.epfl.cs107.icmon.actor.items;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        2/12/2023
 */

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.signal.logic.Or;

public abstract class ICMonItem extends CollectableAreaEntity {
    Sprite sprite;

    public ICMonItem(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);
        sprite = new RPGSprite(spriteName,1f,1f,this);
    }
    public ICMonItem(Area area, DiscreteCoordinates position, String spriteName) {
        super(area, Orientation.DOWN, position);
        sprite = new RPGSprite(spriteName,1f,1f,this);
    }

    @Override
    public boolean takeCellSpace() {
        return true; //non traversable
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
}
