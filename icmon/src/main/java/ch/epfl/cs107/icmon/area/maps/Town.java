package ch.epfl.cs107.icmon.area.maps;

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Window;

public final class Town extends ICMonArea {
    @Override
    public String getTitle() {
        return "town";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new ICShopAssistant(this,new DiscreteCoordinates(8,8)));
        registerActor(new Door(this, "lab", new DiscreteCoordinates(6,2), new DiscreteCoordinates(15,24)));
        registerActor(new Door(this, "arena", new DiscreteCoordinates(4,2), new DiscreteCoordinates(20,16)));
        registerActor(new Door(this, "house", new DiscreteCoordinates(3,2), new DiscreteCoordinates(7,27)));
        registerActor(new Door(this, "shop", new DiscreteCoordinates(3,2), new DiscreteCoordinates(25,20)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5,5);
    }
}
