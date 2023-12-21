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
import java.util.Arrays;
import java.util.List;

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
     * Default Pokemon constructor.
     *
     * @param area         The owner area. Not null.
     * @param position     The initial position of the entity. Not null.
     * @param name         The name of the Pokemon.
     * @param attackDamage The number of damage inflicted to the opponent.
     * @param maxHp        The maximum health level that can reach the Pokémon.
     */
    public Pokemon(Area area, DiscreteCoordinates position, String name, int attackDamage, int maxHp) {
        super(area, Orientation.DOWN, position);
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.attackDamage = attackDamage;
        sprite = new RPGSprite("pokemon/" + name, 1, 1, this);
    }

    /**
     * @return an ArrayList with ICMonFightActions
     */
    public abstract ArrayList<ICMonFightAction> getActions();

    @Override
    public boolean hasToLeaveArea() {
        return true;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return new ArrayList<>(Arrays.asList(this));
    }

    /**
     * Inflicts damage on the Pokémon, reducing its current health.
     *
     * @param takenDamage The amount of damage taken. Must be greater than 0.
     */
    public void receiveDamage(int takenDamage){
        if(takenDamage > 0){
            hp -= takenDamage;
            if (hp < 0){
                hp = 0;
            }
        }
    }

    /**
     * Heals the Pokémon a certain amount of hp
     *
     * @param healingValue The amount of heal. Must be greater than 0.
     */
    public void heal(int healingValue){
        if(healingValue > 0){
            hp += healingValue;
            if (hp > maxHp){
                hp = maxHp;
            }
        }
    }

    /**
     * modifies the attack damage of the Pokémon,
     * can not be lower than 1
     * @param amount  The amount of the attack buff/nerf
     */
    public void modifieAttack(int amount){
        attackDamage += amount;
        if (attackDamage <= 1){
            attackDamage = 1;
        }
    }

    /**
     * Checks if it's dead
     *
     * @return 'true' if it's dead, 'false' otherwise.
     */
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

    /**
     * Retrieves the attack action of the Pokémon.
     *
     * @return An ICMonFightAction representing the Pokémon's attack, or null if not an Attack.
     */
    public Attack getAttack(){
        for (ICMonFightAction action : getActions()){
            if (action instanceof Attack){
                return (Attack) action;
            }
        }
        return null;
    }

    /**
     * @return A copy of the Pokémon's properties
     */
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
    /**
     * Update method to make Attack action's damage updatable
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        getAttack().setAttackDamage(properties().damage());
    }
}