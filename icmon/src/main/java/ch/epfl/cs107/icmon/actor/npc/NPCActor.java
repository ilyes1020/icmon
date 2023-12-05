package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        5/12/2023
 */

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

import java.util.List;

public abstract class NPCActor extends ICMonActor {
     Sprite sprite;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param sprite_name (Name): Name of the sprite. Not null
     */
    public NPCActor(Area area, Orientation orientation, DiscreteCoordinates position, String sprite_name) {
        super(area, orientation, position);
        sprite = new RPGSprite(sprite_name , 1, 1.3125f, this , new RegionOfInterest(0, 0, 16,
                21));
    }

    @Override
    public boolean takeCellSpace() { //non traversable
        return true;
    }

    @Override
    public boolean isCellInteractable() { //non interactable par contact
        return false;
    }

    @Override
    public boolean isViewInteractable() { //interactable par la vue
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return super.getCurrentCells();
    }
    //no need to Override the getCurrentCell method, same as super
}
