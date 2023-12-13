package ch.epfl.cs107.icmon.gamelogic.fights;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightPokemonSelectionGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public class PokemonSelectionMenu extends PauseMenu implements PauseMenuSelector {

    private boolean isRunning;
    private ArrayList<Pokemon> playersPokemonList;
    private Keyboard keyboard;
    private ICMonFightPokemonSelectionGraphics selectionGraphics;

    private Pokemon pokemonChosen;
    public PokemonSelectionMenu(ArrayList<Pokemon> playersPokemonList){
        isRunning = true;
        this.playersPokemonList =playersPokemonList;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {

            keyboard = getKeyboard();
            selectionGraphics = new ICMonFightPokemonSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemonList);
            return true;

        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        selectionGraphics.update(deltaTime);
        if (selectionGraphics.choice()!=null){
            pokemonChosen=selectionGraphics.choice();
        }
        if (keyboard.get(Keyboard.ENTER).isPressed()){
            isRunning = false;
            end();
        }
    }
    public Pokemon getPokemonChosen(){
        return pokemonChosen;
    }
    @Override
    protected void drawMenu(Canvas c) {
        selectionGraphics.draw(c);
    }

    public boolean isRunning(){
        return (isRunning);
    }
}
