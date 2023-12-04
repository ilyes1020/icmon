package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        29/11/2023
 */

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public final class Town extends ICMonArea {

    private ICMonEvent event;

    @Override
    public String getTitle() {
        return "town";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        ICBall balle = new ICBall(this, new DiscreteCoordinates(6,6),"items/icball");
        registerActor(balle);
        event = new CollectItemEvent(balle);
        event.onStart(new LogAction("CollectItemEvent started !"));
        event.onComplete(new LogAction("CollectItemEvent completed !"));
        event.start();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        event.update(deltaTime);
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5,5);
    }
}
