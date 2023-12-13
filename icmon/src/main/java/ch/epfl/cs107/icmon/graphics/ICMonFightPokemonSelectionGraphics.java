package ch.epfl.cs107.icmon.graphics;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

public final class ICMonFightPokemonSelectionGraphics extends ICMonFightInteractionGraphics implements Updatable {
    private static final float FONT_SIZE = .6f;

    private final Keyboard keyboard;
    private final float scalefactor;
    private final Pokemon[] pokemons;

    private final GraphicsEntity[] selectors;

    private final Graphics header;

    private Pokemon choice;

    private int currentChoice;

    public ICMonFightPokemonSelectionGraphics(float scaleFactor, Keyboard keyboard, List<Pokemon> pokemons) {
        super(scaleFactor);
        assert !pokemons.isEmpty();
        this.keyboard = keyboard;
        this.scalefactor = scaleFactor;
        this.pokemons = pokemons.toArray(new Pokemon[0]);
        selectors = new GraphicsEntity[3];
        header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 2 + 4f), new TextGraphics("Please, select a Pokemon", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
        currentChoice = 0;
    }

    @Override
    public void update(float deltaTime) {
        // HR : Keyboard management
        if (keyboard.get(Keyboard.LEFT).isPressed()){
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed())
            currentChoice = min(currentChoice + 1, pokemons.length - 1);
        else if (keyboard.get(Keyboard.ENTER).isPressed())
            choice = pokemons[currentChoice];
        // HR : Prepare the left selector
        if (currentChoice == 0){
            selectors[0] = null;
        } else {
            var spriteName = "pokemon/" + pokemons[currentChoice-1].properties().name();
            var scale = scalefactor;
            var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
            image.setAlpha(.6f);
            selectors[0] = new GraphicsEntity(new Vector(scale / 3 - 5f, scale / 2 - 4f), image);        }

        // HR : Prepare the middle selector
        var spriteName = "pokemon/" + pokemons[currentChoice].properties().name();
        var scale = scalefactor;
        var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
        image.setAlpha(1f);
        selectors[1] = new GraphicsEntity(new Vector(scale / 3, scale / 2 - 4f), image);
        // HR : Prepare the Right selector
        if (currentChoice == pokemons.length - 1 ){
            selectors[2] = null;
        } else {
            spriteName = "pokemon/"+ pokemons[currentChoice+1].properties().name();
            scale = scalefactor;
            image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale/2, scale/2);
            image.setAlpha(.6f);
            selectors[2] = new GraphicsEntity(new Vector(scale / 3 + 5f, scale / 2 - 4f),
                    image);
        }
    }

    public Pokemon choice(){
        return choice;
    }

    @Override
    public void draw(Canvas canvas) {
        // HR : Draw the header
        header.draw(canvas);
        // HR : Draw the selectors that are visible (not null)
        for (var selector : selectors)
            if(nonNull(selector))
                selector.draw(canvas);
    }
}
