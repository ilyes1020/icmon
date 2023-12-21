package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Window;

public abstract class ICMonArea extends Area {

    /**
     * Setup specific background, foreground, and actors for the specific area
     */
    protected abstract void createArea();

    /**
     * @return DiscreteCoordinates of the spawn position of the specific area
     */
    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    /**
     * In order to have a music in each area
     * @param audio the current window
     */
    public void setAreaMusic(Audio audio) {
        SoundAcoustics background = new SoundAcoustics("sound/"+getTitle()+"_music.wav", 0.3f, false,false,true, true);
        background.shouldBeStarted();
        background.bip(audio);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new ICMonBehavior(window, getTitle()));
            createArea();
            return true;
        }
        return false;
    }

    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }

}
