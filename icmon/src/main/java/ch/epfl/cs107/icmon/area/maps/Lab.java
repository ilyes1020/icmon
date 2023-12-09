package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.areagame.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;

public class Lab extends ICMonArea {
    @Override
    public String getTitle() {
        return "lab";
    }

    @Override
    protected void createArea() {
        registerActor(new Door(this, "town", new DiscreteCoordinates(15,23), new DiscreteCoordinates(6,1), new DiscreteCoordinates(7,1)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(6,2);
    }
}
