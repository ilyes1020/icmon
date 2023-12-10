package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        5/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public class ICShopAssistant extends NPCActor{

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICShopAssistant(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, "actors/assistant");
    }

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }
    private class ICShopAssistantHandler implements ICMonInteractionVisitor{

    }
}
