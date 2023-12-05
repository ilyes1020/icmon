package ch.epfl.cs107.icmon.actor.player;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        29/11/2023
 */

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class ICMonPlayer extends ICMonActor implements Interactor {

    private final ICMonPlayerInteractionHandler handler;

    private final static int ANIMATION_DURATION = 8;

    private final OrientedAnimation walkingAnimation;
    private final OrientedAnimation surfingAnimation;
    private OrientedAnimation currentAnimation;
    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICMonPlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        walkingAnimation = new OrientedAnimation("actors/player", ANIMATION_DURATION /2, Orientation.DOWN, this);
        surfingAnimation = new OrientedAnimation("actors/player_water", ANIMATION_DURATION /2, Orientation.DOWN, this);
        currentAnimation = walkingAnimation;
        handler = new ICMonPlayerInteractionHandler();
    }
    @Override
    public void update(float deltaTime) {

        Keyboard keyboard = getOwnerArea().getKeyboard();
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        if (isDisplacementOccurs()){
            currentAnimation.update(deltaTime);       //update l'animation si ya un déplacement
        }
        else {
            currentAnimation.reset();      //reset l'animation quand on ne bouge pas
        }
        super.update(deltaTime);
    }

    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                move(ANIMATION_DURATION);
                currentAnimation.orientate(getOrientation());  //oriente le perso
            }
        }
    }

    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    } //ne soit pas traversable

    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
    }

    /**
     * Get this Interactor's current field of view cells coordinates
     *
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates ().jump( getOrientation (). toVector ()));
    }

    /**
     * @return (boolean): true if this require cell interaction
     */
    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    /**
     * @return (boolean): true if this require view interaction
     */
    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (keyboard.get(Keyboard.L).isPressed()){
            return true;
        }
        return false;
    }

    /**
     * Do this Interactor interact with the given Interactable
     * The interaction is implemented on the interactor side !
     *
     * @param other             (Interactable). Not null
     * @param isCellInteraction True if this is a cell interaction
     */
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler , isCellInteraction);
    }
    //no need to Override the getCurrentCell method, same as super

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        (( ICMonInteractionVisitor ) v). interactWith (this , isCellInteraction );
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor{
        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {  //ramasser la balle
            if (!isCellInteraction && wantsCellInteraction()){
                ball.collect();
            }
        }
        @Override
        public void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
            //System.out.println(cell.getType().getWalkingType() == ICMonBehavior.AllowedWalkingType.FEET);
            if (isCellInteraction){
                if (cell.getType().getIsWalkable() == ICMonBehavior.AllowedWalkingType.FEET){ //est-ce qu'il y a plus simple que de créer des getter ?
                    currentAnimation = walkingAnimation;
                }
                if (cell.getType().getIsWalkable() == ICMonBehavior.AllowedWalkingType.SURF){
                    currentAnimation = surfingAnimation;
                }
            }
        }
    }
}
