package ch.epfl.cs107.icmon.gamelogic.fights;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICMonFight extends PauseMenu{

    private ICMonPlayer player;
    private Pokemon playersPokemon;
    private Pokemon opponent;
    private ICMonFightArenaGraphics arena;
    private FightStage stage;
    private float counter = 5f; //for the update method
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
    public ICMonFight(ICMonPlayer player, Pokemon playersPokemon, Pokemon opponent){
        this.player = player;
        this.playersPokemon = playersPokemon;
        this.opponent = opponent;
        this.isRunning = true;
        arena = new ICMonFightArenaGraphics (CAMERA_SCALE_FACTOR, playersPokemon.properties(), opponent.properties());
        stage = FightStage.INTRODUCTION;
        keyboard = player.keyboard;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        switch (stage){
            case INTRODUCTION :
                arena.setInteractionGraphics (new ICMonFightTextGraphics( CAMERA_SCALE_FACTOR , "Welcome to the fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    this.stage = FightStage.ACTIONSELECT;
                }
                break;

            case ACTIONSELECT:
                counter -= deltaTime;
                System.out.println(counter);
                playerAction = playersPokemon.getActions().get(0);
                this.stage = FightStage.ACTIONEXECUTION;
                break;

            case ACTIONEXECUTION:
                if (opponent.isDead() || !playerAction.doAction(opponent)){
                    playerDidAction = false;
                    this.stage = FightStage.CONCLUSION;
                }else{
                    this.stage = FightStage.OPPONENTACTION;
                }
                break;

            case OPPONENTACTION:
                int i = 0;
                while (opponent.getActions().get(i) == null){
                    i++;
                }
                opponentAction = opponent.getActions().get(i);
                if (playersPokemon.isDead() || !opponentAction.doAction(playersPokemon)){
                    opponentDidAction = false;
                    this.stage = FightStage.CONCLUSION;
                }else{
                    this.stage = FightStage.ACTIONSELECT;
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
    public void clearActions(){
        playerAction = null;
        opponentAction = null;
    }

    public boolean isRunning(){
        return (isRunning);
    }

    @Override
    protected void drawMenu(Canvas c) {
        arena.draw(c);
    }
}
