package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.graphics.ICMonFightPokemonSelectionGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import java.util.List;

/**
 * Menu for selecting a Pokémon before a fight
 * Extends PauseMenu and implements PauseMenuSelector
 */
public class PokemonSelectionMenu extends PauseMenu implements PauseMenuSelector {

    private boolean isRunning;
    private List<Pokemon> playersPokemonList;
    private Keyboard keyboard;
    private ICMonFightPokemonSelectionGraphics selectionGraphics;
    private Pokemon chosenPokemon;

    /**
     * Constructor for creating a PokemonSelectionMenu.
     *
     * @param playersPokemonList The list of Pokemon available for selection.
     */
    public PokemonSelectionMenu(List<Pokemon> playersPokemonList){
        isRunning = true;
        this.playersPokemonList =playersPokemonList;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem){
        if (super.begin(window, fileSystem)) {

            keyboard = getKeyboard();
            selectionGraphics = new ICMonFightPokemonSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playersPokemonList);
            SoundAcoustics music = new SoundAcoustics("sound/fight_music.wav", 0.3f, false,false,true, true);
            music.shouldBeStarted();
            music.bip(window);
            return true;

        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        selectionGraphics.update(deltaTime);
        if (selectionGraphics.choice()!=null){
            chosenPokemon =selectionGraphics.choice();
        }
        if (keyboard.get(Keyboard.ENTER).isPressed()){
            isRunning = false;
            end();
        }
    }

    /**
     * Gets the Pokemon chosen by the player.
     *
     * @return The chosen Pokemon.
     */
    public Pokemon getChosenPokemon(){
        return chosenPokemon;
    }
    @Override
    protected void drawMenu(Canvas c) {
        selectionGraphics.draw(c);
    }
    @Override
    public boolean isRunning(){
        return (isRunning);
    }
}
