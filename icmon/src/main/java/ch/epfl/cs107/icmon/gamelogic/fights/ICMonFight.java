package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.items.ICBerry;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.actions.OnSelfAction;
import ch.epfl.cs107.icmon.actor.pokemon.actions.OnTargetAction;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Represents a Pokémon fight in the ICMon game
 * Extends from PauseMenu and implements PauseMenuSelector.
 */
public class ICMonFight extends PauseMenu implements PauseMenuSelector{

    private Pokemon playersPokemon;
    private Pokemon opponent;
    private ICMonFightArenaGraphics arena;
    private ICMonFightActionSelectionGraphics selectionGraphics;
    private FightStage stage;
    private Keyboard keyboard;
    private boolean isRunning;
    private ICMonFightAction playerAction;
    private ICMonFightAction opponentAction;
    private boolean playerDidAction;
    private boolean opponentDidAction;
    private final int[] playerBerryNb;

    /**
     * Enum representing the different stages of a Pokemon fight
     */
    public enum FightStage {
        INTRODUCTION,
        ACTIONSELECT,
        ACTIONEXECUTION,
        OPPONENTACTION,
        CONCLUSION;
    }

    /**
     * Constructor for creating an ICMonFight.
     *
     * @param playersPokemon The Pokémon controlled by the player.
     * @param opponent       The opponent Pokémon in the fight.
     */
    public ICMonFight(Pokemon playersPokemon, Pokemon opponent, int[] playerBerryNb){
        this.playersPokemon = playersPokemon;
        this.opponent = opponent;
        this.playerBerryNb = playerBerryNb;
        this.isRunning = true;
        stage = FightStage.INTRODUCTION;
    }

    /**
     * Updates the state of the fight over time, changes the fight stages and graphics while the player is fighting
     *
     * @param deltaTime The time elapsed since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //update pokémon so actions can increase or decrease pokémon stats
        playersPokemon.update(deltaTime);
        opponent.update(deltaTime);

        //Managing the usage of berries, if used, skip player's action Stage
        if (keyboard.get(Keyboard.F).isPressed()){
            if (playerBerryNb[0] > 0){
                --playerBerryNb[0];
                playersPokemon.heal(ICBerry.HEALING_VALUE);
                stage = FightStage.OPPONENTACTION;
                arena.setPlayerBerryNb(playerBerryNb[0]);
                arena.update(deltaTime);
            }else{
                System.out.println("No more berries !");
            }
        }
        switch (stage){

            //stage to introduce the fight
            case INTRODUCTION :
                arena.setInteractionGraphics (new ICMonFightTextGraphics( CAMERA_SCALE_FACTOR , "Welcome to the fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    stage = FightStage.ACTIONSELECT;
                }
                break;

            //stage when the player selects an action
            case ACTIONSELECT:

                arena.setInteractionGraphics (selectionGraphics);
                selectionGraphics.update(deltaTime);

                if (selectionGraphics.choice() != null) {
                    playerAction = selectionGraphics.choice();
                    stage = FightStage.ACTIONEXECUTION;
                }
                break;

            //stage when the player execute the selected action
            case ACTIONEXECUTION:

                //checks the type of the action, and cast it on the players pokemon if it is an OnSelfAction or the opponent otherwise
                if (playerAction instanceof OnSelfAction){
                    playerDidAction = playerAction.doAction(playersPokemon);
                }else if (playerAction instanceof OnTargetAction){
                    playerDidAction = playerAction.doAction(opponent);
                }
                if(opponent.isDead() || !playerDidAction){
                    stage = FightStage.CONCLUSION;
                }
                else{
                    stage = FightStage.OPPONENTACTION;
                }
                break;

            //stage when the opponent can do an action
            case OPPONENTACTION:

                if (opponent.getAttack() != null){
                    opponent.getAttack().doAction(playersPokemon);
                    if (!playersPokemon.isDead()){
                        stage = FightStage.ACTIONSELECT;
                        selectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemon.getActions());
                    }
                    else {
                        stage = FightStage.CONCLUSION;
                    }
                }else{
                    stage = FightStage.CONCLUSION;
                }
                break;

            //stage to conclude the fight
            case CONCLUSION :
                if (opponent.isDead()) {
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The Player has won the fight"));
                }
                else if (playersPokemon.isDead()){
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent has won the fight"));
                }
                else if (!playerDidAction){
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player decided not to continue the fight"));
                }
                else if (!opponentDidAction){
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent decided not to continue the fight"));
                }
                if (keyboard.get(Keyboard.SPACE).isPressed()){
                    isRunning = false;
                    end();
                }
                break;
        }
    }

    /**
     * Starts the fight, allows the player to go through stages with space key
     * Creates fight graphics
     *
     * @param window (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return true if the fight has begun successfully
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {

            keyboard = getKeyboard();

            arena = new ICMonFightArenaGraphics (CAMERA_SCALE_FACTOR, playersPokemon.properties(), opponent.properties(), playerBerryNb[0]);
            selectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemon.getActions());

            return true;
        }
        return false;
    }

    @Override
    public boolean isRunning(){
        return (isRunning);
    }

    @Override
    protected void drawMenu(Canvas c) {
        arena.draw(c);
    }
}
