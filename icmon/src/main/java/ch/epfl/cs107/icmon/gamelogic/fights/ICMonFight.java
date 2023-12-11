package ch.epfl.cs107.icmon.gamelogic.fights;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.window.Canvas;

public class ICMonFight extends PauseMenu{

    private float counter = 5f; //for the update method

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        counter -= deltaTime;
    }

    public boolean isRunning(){
        return (counter>0);
    }

    @Override
    protected void drawMenu(Canvas c) {
    }
}
