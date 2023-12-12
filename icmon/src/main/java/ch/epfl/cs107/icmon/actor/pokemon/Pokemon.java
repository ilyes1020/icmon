package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor implements ICMonFightableActor {

    private String name;
    private int hp;
    private int maxHp;
    private int attackDamage;
    private Sprite sprite;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pokemon(Area area, DiscreteCoordinates position, String name, int attackDamage, int maxHp) {
        super(area, Orientation.DOWN, position);
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.attackDamage = attackDamage;
        sprite = new RPGSprite("pokemon/" + name, 1, 1, this);
    }

    //getCurrentCells(),
    //takeCellSpace(), //return false
    //isCellInteractable(), //return true
    //isViewInteractable() //return false
    //(all three same as super method)

    public abstract ArrayList<ICMonFightAction> getActions(); //comme a dit Edgoat

    public void receiveDamage(int takenDamage){
        if(takenDamage > 0){
            hp -= takenDamage;
            if (hp < 0){
                hp = 0;
            }
        }
    }

    public boolean isDead(){
        return (hp == 0);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith (this , isCellInteraction );
    }
    public ICMonFightAction getAttack(){
        for (ICMonFightAction action : getActions()){
            if (action instanceof Attack){
                return action;
            }
        }
        return null;
    }
//    public boolean canAttack(){
//        if (getActions().stream().anyMatch(action -> action instanceof Attack)){
//            return true;
//        }
//        return false;
//    }

    public PokemonProperties properties(){
        return new PokemonProperties();
    }

    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {
        public String name(){
            return name;
        }
        public float hp(){
            return hp;
        }
        public float maxHp(){
            return maxHp;
        }
        public int damage(){
            return attackDamage;
        }
    }
}