package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public abstract class NPCActor extends ICMonActor {
     private Sprite sprite;

     protected static final int ANIMATION_DURATION = 8;

     private OrientedAnimation currentAnimation;

     private DiscreteCoordinates target;
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
        // Easier to move the NPCs with this
        currentAnimation = new OrientedAnimation(sprite_name,ANIMATION_DURATION/2,Orientation.DOWN,this);
    }

    /**
     * Update the movements
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {

        if (isDisplacementOccurs()){
            currentAnimation.update(deltaTime);
        }
        else {
            currentAnimation.reset();
        }
        if (target!=null){
            moveTo(target);
        }
        super.update(deltaTime);
    }

    /**
     * Set a target coordinates
     * @param target (DiscreteCoordinates) coordinates to focus
     */
    public void setTarget(DiscreteCoordinates target){
        this.target =target;
    }

    /**
     * Check if the NPC is targeting
     * @return (boolean)
     */
    public boolean isTargeting(){
        return target!=null;
    }

    /**
     * Set target attribut to null
     */
    public void stopTargeting(){
        target=null;
    }

    /**
     * Move with the computed orientation
     * @param orientation the orientation the boy will have
     */
    private void move(Orientation orientation){
        orientate(orientation);
        super.move(ANIMATION_DURATION);
    }
    /**
     * Move to a specific coordinates
     * @param target (DiscreteCoordinates) the boy will go to
     */
    public void moveTo(DiscreteCoordinates target){
        // Check the side coordinates of the boy
        DiscreteCoordinates currentCell = getCurrentCells().get(0);
        DiscreteCoordinates left = currentCell.left();
        DiscreteCoordinates right = currentCell.right();
        DiscreteCoordinates up = currentCell.up();
        DiscreteCoordinates down = currentCell.down();

        // Check the closest path to the target and go with it
        float distanceLeft = DiscreteCoordinates.distanceBetween(target, left);
        float distanceRight = DiscreteCoordinates.distanceBetween(target, right);
        float distanceUp = DiscreteCoordinates.distanceBetween(target, up);
        float distanceDown = DiscreteCoordinates.distanceBetween(target, down);
        if(!isDisplacementOccurs()){
            if (distanceUp < distanceDown) {
                move(Orientation.UP);
            } else if (distanceUp > distanceDown){
                move(Orientation.DOWN);
            }
            if (distanceLeft < distanceRight) {
                move(Orientation.LEFT);
            } else if (distanceLeft > distanceRight){
                move(Orientation.RIGHT);
            }
        }
        currentAnimation.orientate(getOrientation());
    }
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }
    @Override
    public void draw(Canvas canvas) {
        currentAnimation.draw(canvas);
    }
}
