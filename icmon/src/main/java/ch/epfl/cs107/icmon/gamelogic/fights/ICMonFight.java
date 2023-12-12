package ch.epfl.cs107.icmon.gamelogic.fights;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;

public class ICMonFight extends PauseMenu{

    private Pokemon player;
    private Pokemon opponent;

    private float counter = 5f; //for the update method

    public ICMonFight(Pokemon player, Pokemon opponent){
        this.player = player;
        this.opponent = opponent;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        counter -= deltaTime;
    }

    public boolean isRunning(){
        return (counter > 0);
    }

    @Override
    protected void drawMenu(Canvas c) {
        ICMonFightArenaGraphics arena = new ICMonFightArenaGraphics ( CAMERA_SCALE_FACTOR , player.properties(), opponent.properties());
        arena.setInteractionGraphics (new ICMonFightTextGraphics( CAMERA_SCALE_FACTOR ,
                "hello world"));
        arena.draw(c);
    }
}
