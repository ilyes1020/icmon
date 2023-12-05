package ch.epfl.cs107.icmon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        19/11/2023
 */

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.LogAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public final class ICMon extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 13.f;

    private final String[] areas = {"town"};
    private ICMonPlayer player;

    private ArrayList <ICMonEvent> eventList = new ArrayList<ICMonEvent>();

    private int areaIndex;

    private ICMonEvent event;

    private void createAreas() {
        addArea(new Town());
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            areaIndex = 0;
            initArea(areas[areaIndex]);
            ICBall balle = new ICBall(getCurrentArea(), new DiscreteCoordinates(6,6),"items/icball");
            event = new CollectItemEvent(balle,player);
            eventList.add(event);
            event.onStart(new LogAction("CollectItemEvent started !"));
            event.onStart(new RegisterinAreaAction(getCurrentArea(),balle));
            event.onComplete(new LogAction("CollectItemEvent completed !"));
            event.start();

            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getCurrentArea().getKeyboard(); //pour reset le jeu
        if (keyboard.get(Keyboard.R).isPressed()){ //isPressed pour pas que ca spam
            begin(getWindow(),getFileSystem());
        }
        event.update(deltaTime);
        super.update(deltaTime);
    }

    @Override
    public void end() {

    }

    @Override
    public String getTitle() {
        return "ICmon";
    }

    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        player = new ICMonPlayer(area, Orientation.DOWN, coords);
        player.enterArea(area, coords);
        player.centerCamera();

    }

//    private void switchArea() {
//        player.leaveArea();
//        areaIndex = (areaIndex == 0) ? 1 : 0;
//        ICMonArea currentArea = (ICMonArea) setCurrentArea(areas[areaIndex], false);
//        player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());
//        player.strengthen();
//    }

}
