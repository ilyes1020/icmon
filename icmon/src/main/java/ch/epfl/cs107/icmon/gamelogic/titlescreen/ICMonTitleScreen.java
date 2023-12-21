package ch.epfl.cs107.icmon.gamelogic.titlescreen;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.gamelogic.fights.PauseMenuSelector;
import ch.epfl.cs107.icmon.graphics.ICMonTitleScreenGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICMonTitleScreen extends PauseMenu implements PauseMenuSelector {
    private boolean isRunning;
    private Keyboard keyboard;
    private ICMonTitleScreenGraphics menuGraphics;

    /**
     * Constructor for creating a PokemonSelectionMenu.
     */
    public ICMonTitleScreen(){
        isRunning = true;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {

            keyboard = getKeyboard();
            menuGraphics = new ICMonTitleScreenGraphics(CAMERA_SCALE_FACTOR);
            SoundAcoustics music = new SoundAcoustics("sound/main_menu_music.wav", 0.4f, false,false,true, true);
            music.shouldBeStarted();
            music.bip(window);
            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (keyboard.get(Keyboard.ENTER).isPressed()){
            isRunning = false;
            end();
        }
    }
    @Override
    protected void drawMenu(Canvas c) {
        menuGraphics.draw(c);
    }
    @Override
    public boolean isRunning(){
        return (isRunning);
    }
}
