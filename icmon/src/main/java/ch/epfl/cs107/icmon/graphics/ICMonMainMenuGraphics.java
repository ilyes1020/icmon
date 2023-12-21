package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.window.Canvas;

import static ch.epfl.cs107.play.io.ResourcePath.getBackground;

public class ICMonMainMenuGraphics implements Graphics {
    private ImageGraphics background;
    public ICMonMainMenuGraphics(float scaleFactor){
        background = new ImageGraphics(getBackground("main_menu"), scaleFactor, scaleFactor);
        background.setRelativeTransform(Transform.I.translated(0, 0));
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);
    }
}
