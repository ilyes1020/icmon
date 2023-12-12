package ch.epfl.cs107.icmon.gamelogic.fights;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICMonFight extends PauseMenu{

    private Pokemon playersPokemon;
    private Pokemon opponent;
    private ICMonFightArenaGraphics arena;
    private ICMonFightActionSelectionGraphics selectionGraphics;
    private FightStage stage;
    private Keyboard keyboard;
    private boolean isRunning;
    private ICMonFightAction playerAction;
    private ICMonFightAction opponentAction;

    //ces 2 pas sûr d'avoir besoin
    private boolean playerDidAction;
    private boolean opponentDidAction;

    public enum FightStage {
        INTRODUCTION,
        ACTIONSELECT,
        ACTIONEXECUTION,
        OPPONENTACTION,
        CONCLUSION;
    }
    public ICMonFight(Pokemon playersPokemon, Pokemon opponent){
        this.playersPokemon = playersPokemon;
        this.opponent = opponent;
        this.isRunning = true;
        stage = FightStage.INTRODUCTION;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        switch (stage){
            case INTRODUCTION :
                arena.setInteractionGraphics (new ICMonFightTextGraphics( CAMERA_SCALE_FACTOR , "Welcome to the fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    stage = FightStage.ACTIONSELECT;
                }
                break;

            case ACTIONSELECT:

                arena.setInteractionGraphics (selectionGraphics);
                selectionGraphics.update(deltaTime);

                if (selectionGraphics.choice() != null) {
                    playerAction = selectionGraphics.choice();
                    stage = FightStage.ACTIONEXECUTION;
                }
                break;

            case ACTIONEXECUTION:

                playerDidAction = playerAction.doAction(opponent);

                if(opponent.isDead() || !playerDidAction){
                    stage = FightStage.CONCLUSION;
                }
                else{
                    stage = FightStage.OPPONENTACTION;
                }
                break;

            case OPPONENTACTION:

                if (opponent.getAttack() != null){
                    opponent.getAttack().doAction(playersPokemon);
                    if (!playersPokemon.isDead())
                        stage = FightStage.ACTIONSELECT;
                        selectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemon.getActions());
                }else{
                    stage = FightStage.CONCLUSION;
                }
                break;

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
    //pas sûr d'avoir besoin
    public void clearActions(){
        playerAction = null;
        opponentAction = null;
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {

            keyboard = getKeyboard();

            arena = new ICMonFightArenaGraphics (CAMERA_SCALE_FACTOR, playersPokemon.properties(), opponent.properties());
            selectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemon.getActions());

            return true;
        }
        return false;
    }

    public boolean isRunning(){
        return (isRunning);
    }

    @Override
    protected void drawMenu(Canvas c) {
        arena.draw(c);
    }
}
