package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon.PokemonProperties;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

import static ch.epfl.cs107.play.io.ResourcePath.getBackground;
import static ch.epfl.cs107.play.io.ResourcePath.getSprite;
import static java.util.Objects.nonNull;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public final class ICMonFightArenaGraphics implements Graphics, Updatable{

    private final ImageGraphics background;
    private static final float FONT_SIZE = .6f;
    private final float scaleFactor;
    private int playerBerryNb;
    /** ??? */
    private final GraphicsEntity player;
    /** ??? */
    private final GraphicsEntity opponent;
    //berry graphic
    private final GraphicsEntity berry;
    //berry number text
    private final TextGraphics berryNbText;
    //berry number graphic
    private GraphicsEntity berryNb;
    private final ICMonFightInfoGraphics playerInfo;
    private final ICMonFightInfoGraphics opponentInfo;
    private final GraphicsEntity berryKeybind;
    private ICMonFightInteractionGraphics interactionGraphics;

    public ICMonFightArenaGraphics(float scaleFactor, PokemonProperties player, PokemonProperties opponent, int playerBerryNb) {
        this.scaleFactor = scaleFactor;
        // HR : The background will be displayed in the top 2/3 part of the screen
        background = new ImageGraphics(getBackground("fight"), scaleFactor, scaleFactor * 2 / 3);
        background.setRelativeTransform(Transform.I.translated(0, scaleFactor / 3));
        // HR : Add the pokemon's fight sprite
        this.playerBerryNb = playerBerryNb;
        this.opponent = new GraphicsEntity(new Vector(scaleFactor * 2 / 3 -.5f, scaleFactor * 2 / 3 - .5f), new ImageGraphics(getSprite("fight/" + opponent.name()), 5, 5, new RegionOfInterest(0, 0, 64, 64), true));
        this.player = new GraphicsEntity(new Vector(0f, scaleFactor / 3), new ImageGraphics(getSprite("fight/" + player.name()), 5, 5, new RegionOfInterest(128, 0, 64, 64), true));
        this.berry = new GraphicsEntity(new Vector(0.2f, scaleFactor/2), new ImageGraphics(getSprite("fight/icberry"), 1, 1, new RegionOfInterest(0, 0, 64, 64), true));
        // HR : Prepare the info's graphics
        this.opponentInfo = new ICMonFightInfoGraphics(new Vector(0.5f, scaleFactor - 2.5f), opponent);
        this.playerInfo = new ICMonFightInfoGraphics(new Vector(scaleFactor - 6.5f, scaleFactor / 3 + .5f), player);
        // Prepare the berries info's graphics
        this.berryKeybind = new GraphicsEntity(new Vector(0.3f, scaleFactor / 2 - .2f), new TextGraphics("F", FONT_SIZE, Color.BLACK, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
        this.berryNbText = new TextGraphics("" + playerBerryNb, FONT_SIZE, Color.BLACK, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003);
        this.berryNb = new GraphicsEntity(new Vector(1.3f, scaleFactor / 2 + 1.3f), berryNbText);
    }

    public void setInteractionGraphics(ICMonFightInteractionGraphics graphics){
        this.interactionGraphics = graphics;
    }

    @Override
    public void draw(Canvas canvas) {
        // HR : Draw the background
        background.draw(canvas);
        // HR : Draw the pokemons
        player.draw(canvas);
        opponent.draw(canvas);
        // Draw the berry icon and the number of berries
        berry.draw(canvas);
        berryNb.draw(canvas);
        berryKeybind.draw(canvas);
        // HR : Draw the infos
        playerInfo.draw(canvas);
        opponentInfo.draw(canvas);
        if(nonNull(interactionGraphics))
            interactionGraphics.draw(canvas);
    }

    public void setPlayerBerryNb(int playerBerryNb) {
        this.playerBerryNb = playerBerryNb;
    }

    /**
     * Simulates a single time step, updates the text displaying the number of remaining berries
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    public void update(float deltaTime) {
        berryNbText.setText("" + playerBerryNb);
        berryNb = new GraphicsEntity(new Vector(1.3f, scaleFactor / 2 + 1.3f), berryNbText);
    }
}
