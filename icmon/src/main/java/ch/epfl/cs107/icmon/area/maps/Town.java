package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        29/11/2023
 */

import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public final class Town extends ICMonArea {


    @Override
    public String getTitle() {
        return "town";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new ICShopAssistant(this,Orientation.DOWN,new DiscreteCoordinates(8,8)));
        registerActor(new Door(this, "lab", new DiscreteCoordinates(6,2), new DiscreteCoordinates(15,24)));
        registerActor(new Door(this, "arena", new DiscreteCoordinates(4,2), new DiscreteCoordinates(20,16)));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(20,15);
    } //pour le débuggage, normalement 5, 5
}
