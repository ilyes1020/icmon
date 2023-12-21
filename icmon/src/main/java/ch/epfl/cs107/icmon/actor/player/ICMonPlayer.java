package ch.epfl.cs107.icmon.actor.player;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICBerry;
import ch.epfl.cs107.icmon.actor.pokemon.*;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.icmon.message.PassDoorMessage;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ICMonPlayer extends ICMonActor implements Interactor {

    private final ICMonPlayerInteractionHandler handler;
    private Keyboard keyboard;
    private final static int ANIMATION_DURATION = 8;
    private final static int SPRINT_ANIMATION_DURATION = 4;
    private final OrientedAnimation walkingAnimation;
    private final OrientedAnimation surfingAnimation;
    private OrientedAnimation currentAnimation;
    private ICMon.ICMonGameState gameState;
    private Dialog currentDialog;
    private boolean isDialog;
    private List<Pokemon> pokemonList;
    private SoundAcoustics soundItem;
    private SoundAcoustics soundDialog;

    //wrapper for the number of berries, so it can be modified by fights
    private int[] nbBerry = new int [1];

    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     */
    public ICMonPlayer(Area area, Orientation orientation, DiscreteCoordinates position, ICMon.ICMonGameState gameState) {
        super(area, orientation, position);
        walkingAnimation = new OrientedAnimation("actors/player", ANIMATION_DURATION /2, Orientation.DOWN, this);
        surfingAnimation = new OrientedAnimation("actors/player_water", ANIMATION_DURATION /2, Orientation.DOWN, this);
        currentAnimation = walkingAnimation;
        handler = new ICMonPlayerInteractionHandler();
        this.keyboard = getOwnerArea().getKeyboard();
        this.gameState = gameState;
        this.pokemonList = new ArrayList<>();
        soundItem = new SoundAcoustics("sound/collect_item_sound.wav");
        soundDialog = new SoundAcoustics("sound/dialog_sound.wav");
    }

    @Override
    public void bip(Audio audio) {
        soundItem.bip(audio);
        soundDialog.bip(audio);
    }

    /**
     * Updates the player based on the elapsed time (deltaTime),
     * manages dialog behavior and movement,
     * calls the super update method
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        //Managing dialog
        if (isDialog) {
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                soundDialog.shouldBeStarted();
                currentDialog.update(deltaTime);
                if(currentDialog.isCompleted()){
                    clearDialog();
                    isDialog = false;
                }
            }
        }
        //If not in a dialog, move the player if a movement key is pressed
        else {
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.A));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.W));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.D));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.S));
            if (isDisplacementOccurs()){
                currentAnimation.update(deltaTime);       //update the player's animation when the player is moving
            }
            else {
                currentAnimation.reset();                 //reset the player's animation when the player is not moving
            }
            if (keyboard.get(Keyboard.G).isPressed()){
                System.out.println(getCurrentCells().get(0));
            }
        }
        removeDeadPokemon();
        super.update(deltaTime);
    }


    private boolean hasPokemons(){
        return !pokemonList.isEmpty();
    }

    /**
     * Adds a Pokémon to the collection of owned Pokémons.
     *
     * @param pokemon The Pokémon to be added.
     */
    public void addPokemon(Pokemon pokemon){
        if(pokemon != null){
            pokemonList.add(pokemon);
        }
    }

    /**
     * Gets the number of berry
     * @return (int) the number of berry
     */
    public int[] getNbBerry() {
        return nbBerry;
    }

    /**
     * Removes dead Pokemons from the List. Updated
     */
    private void removeDeadPokemon() {
        pokemonList.removeIf(Pokemon::isDead);
    }

    /**
     * This method creates a new dialog instance with the given message
     * and sets isDialog of the player to true to indicate that a dialog is open
     * Used in update()
     *
     * @param message The message to be displayed in the dialog
     */
    public void openDialog(String message){
        currentDialog = new Dialog(message);
        isDialog = true;
    }

    /**
     * @return True if the player is in a dialog, false otherwise
     */
    public boolean isDialog(){
        return isDialog;
    }

    /**
     * Clears the current dialog (sets currentDialog to null)
     */
    public void clearDialog(){
        currentDialog = null;
    }

    /**
     * Moves the player if a specified button is pressed
     *
     * @param orientation The orientation for the player movement
     * @param b The button being pressed
     */
    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                if (keyboard.get(Keyboard.TAB).isDown()){
                    move(SPRINT_ANIMATION_DURATION);
                }
                else{
                    move(ANIMATION_DURATION);
                }
                currentAnimation.orientate(getOrientation());  //orientates the player
            }
        }
    }

    /**
     * Centers the camera on the player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public boolean takeCellSpace() {
        return true;

    }

    @Override
    public void draw(Canvas canvas){
        currentAnimation.draw(canvas);
        if (isDialog){
            currentDialog.draw(canvas);
        }
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
     * @return (boolean): true if this requires cell interaction
     */
    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    /**
     * @return (boolean): true if this requires view interaction
     */
    @Override
    public boolean wantsViewInteraction() {
        return !isDialog && keyboard.get(Keyboard.E).isPressed();
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith (this , isCellInteraction);
    }

    /**
     * Initiates a fight with a fightable opponent,
     * Suspends the current event by sending a suspension message to the game
     *
     * @param opponent The opponent to engage in a fight.
     */
    public void fight(ICMonFightableActor opponent){
        if (hasPokemons()) {
            ICMonEvent selectionEvent = new PokemonSelectionEvent(this, pokemonList, opponent, gameState);
            SuspendWithEventMessage selectionMessage = new SuspendWithEventMessage(selectionEvent, getEventManager());
            gameState.send(selectionMessage);
        }
    }
    /**
     * Do this Interactor interact with the given Interactable
     * The interaction is implemented on the interactor side !
     *
     * @param other (Interactable). Not null
     * @param isCellInteraction True if this is a cell interaction
     */
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        gameState.acceptInteraction(other, isCellInteraction);
    }

    /**
     * Gets the EventManager from gameState
     * @return          The EventManager
     */
    public ICMon.ICMonEventManager getEventManager() {
        return gameState.getEventManager();
    }

    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor{

        /**
         * When the player meets with an ICBall, if it is a view interaction, and the player wants it, collect the ball
         *
         * @param ball                 The ICBall to interact with
         * @param isCellInteraction    Indicates if it's a contact interaction
         */
        @Override
        public void interactWith(ICBall ball, boolean isCellInteraction) {
            if (!isCellInteraction && wantsCellInteraction()){
                soundItem.shouldBeStarted();
                addPokemon(ball.getInsidePokemon());
                ball.collect();
                if (ball.getInsidePokemon() != null) {
                    openDialog("collect_icball_with_"+ ball.getInsidePokemon().properties().name());
                }
                System.out.println("Player is interacting with Ball !");
            }
        }

        /**
         * Describes the interaction between the player and the cell, if it is a contact interaction,
         * changes potentially the animation of the player
         *
         * @param cell                 The cell which the player is interacting with
         * @param isCellInteraction    Indicates if it's a contact interaction
         */
        @Override
        public void interactWith(ICMonBehavior.ICMonCell cell, boolean isCellInteraction) {
            if (isCellInteraction){
                if (cell.getType().getAllowedWalkingType() == ICMonBehavior.AllowedWalkingType.FEET){
                    currentAnimation = walkingAnimation;
                }
                if (cell.getType().getAllowedWalkingType() == ICMonBehavior.AllowedWalkingType.SURF){
                    currentAnimation = surfingAnimation;
                }
            }
        }

        /**
         * When the player interacts with a door, if the interaction is by contact,
         * send a message to the game, which will change the player's area defined by the door
         *
         * @param door                 The Door to interact with
         * @param isCellInteraction    Indicates if it's a contact interaction
         */
        @Override
        public void interactWith (Door door , boolean isCellInteraction ) {
            if (isCellInteraction) {
                PassDoorMessage message = new PassDoorMessage(door, gameState);
                gameState.send(message);
            }
        }

        /**
         * When the player interacts with a Pokémon, initiating a fight if the player has at least one Pokémon
         *
         * @param pokemon              The Pokémon to interact with
         * @param isCellInteraction    Indicates if it's a contact interaction
         */
        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (isCellInteraction){
                fight(pokemon);
            }
        }

        /**
         * When the player meets with an ICBerry, if it is a view interaction, and the player wants it, collect the berry
         *
         * @param berry                 The ICBerry to interact with
         * @param isCellInteraction    Indicates if it's a contact interaction
         */
        @Override
        public void interactWith(ICBerry berry, boolean isCellInteraction){
            if (!isCellInteraction && wantsCellInteraction()){
                berry.collect();
                nbBerry[0]++;
                System.out.println("Berry added to the inventory !");
            }
        }
    }
}
