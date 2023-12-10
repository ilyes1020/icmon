package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public abstract class Pokemon extends ICMonActor {

    private String name;
    private int hp;
    private int maxHp;
    private int inflictedDamage;
    private Sprite sprite;

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public Pokemon(Area area, Orientation orientation, DiscreteCoordinates position, String name, int inflictedDamage, int maxHp) {
        super(area, orientation, position);
        this.name=name;
        this.hp =maxHp;
        this.maxHp=maxHp;
        this.inflictedDamage=inflictedDamage;
        sprite = new RPGSprite("pokemon/" + name, 1, 1, this);
    }

    //getCurrentCells(),takeCellSpace(),isCellInteractable(),isViewInteractable() same as super

    public void receiveDamage(int takenDamage){
        if(takenDamage<0){
            takenDamage=-takenDamage;
        }
        if (hp-takenDamage>=0){
            hp-=takenDamage;
        }
        else {
            hp =0;
        }
    }

    public boolean isDead(){
        return (hp==0);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }


    /**
     * @author Hamza REMMAL (hamza.remmal@epfl.ch)
     */
    public final class PokemonProperties {

        public String name(){
            return null;
        }

        public float hp(){
            return 0f;
        }

        public float maxHp(){
            return 0f;
        }

        public int damage(){
            return 0;
        }

    }

}