package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.ICBully;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Audio;

public class House1 extends ICMonArea {

    @Override
    public String getTitle() {
        return "house1";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door(this, "town", new DiscreteCoordinates(10,12), new DiscreteCoordinates(3,1), new DiscreteCoordinates(4,1)));
        registerActor(new ICBully(this,new DiscreteCoordinates(9,5)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void setAreaMusic(Audio audio) {
        SoundAcoustics background = new SoundAcoustics("sound/house_music.wav", 0.3f, false,false,true, true);
        background.shouldBeStarted();
        background.bip(audio);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(2,2);
    }
}
