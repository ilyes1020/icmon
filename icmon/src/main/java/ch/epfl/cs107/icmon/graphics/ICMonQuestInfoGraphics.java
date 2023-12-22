package ch.epfl.cs107.icmon.graphics;


import ch.epfl.cs107.play.engine.actor.*;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

import static ch.epfl.cs107.play.io.ResourcePath.getSprite;

public class ICMonQuestInfoGraphics extends Node implements Graphics, Attachable {

    private static final float FONT_SIZE = .6f;
    private final ImageGraphics background;
    private final TextGraphics text;
    private ImageGraphics icon;

    /**
     * The reference object
     * @param parent (Positionable): any positionable
     */
    public ICMonQuestInfoGraphics(Positionable parent) {

        Vector anchor = new Vector(-6.5f, 5.4f);

        String DefaultText = "No quest for the moment";

        //Add the background
        background = new ImageGraphics(getSprite("dialog"), 9.0f, 0.8f);
        background.setParent(parent);
        background.setAnchor(anchor);
        //Add the icon
        icon = new ImageGraphics(getSprite("cellOver"), 1, 1, new RegionOfInterest(0, 0, 64, 64), true);
        icon.setParent(parent);
        icon.setAnchor(anchor.add(new Vector(9.0f, -0.1f)));
        //Add the text
        text = new TextGraphics(DefaultText, FONT_SIZE, Color.BLACK, null, 0.0f, false, false, new Vector(0.3f, 1.0f), TextAlign.Horizontal.LEFT, TextAlign.Vertical.TOP, 1.0f, 1001);
        text.setParent(parent);
        text.setAnchor(anchor.add(new Vector(0.3f, 0.9f)));
    }

    @Override
    public void draw(Canvas canvas) {
        //Draw the background
        background.draw(canvas);
        //Draw the text
        text.draw(canvas);
        //Draw the icon
        icon.draw(canvas);
    }

    @Override
    public Transform getTransform() {
        return Transform.I.translated(getPosition().x, getPosition().y);
    }
    @Override
    public Vector getVelocity() {
        return Vector.ZERO;
    }

    /**
     * Gets the text
     * @return (Type : TextGraphics) the text
     */
    public TextGraphics getText(){
        return text;
    }
    /**
     * modifies the text
     * @param text the new text to be displayed (String)
     */
    public void setText(String text){
        this.text.setText(text);
    }
    /**
     * Gets the image
     * @return (Type : ImageGraphics) the icon
     */
    public ImageGraphics getIconImage(){
        return icon;
    }

    /**
     * modifies the icon
     * @param spriteName the new icons name to be displayed(String)
     */
    public void setIconImage(String spriteName){
        icon.setName(getSprite(spriteName));
    }
    public void setVoidIconImage(String spriteName){
        icon.setName(getSprite("cellOver"));
    }
}


