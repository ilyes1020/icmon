package ch.epfl.cs107.icmon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        19/11/2023
 */

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Town;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.CollectItemEvent;
import ch.epfl.cs107.icmon.gamelogic.events.EndOfTheGameEvent;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public final class ICMon extends AreaGame {

    public final static float CAMERA_SCALE_FACTOR = 13.f;

    private final String[] areas = {"town"};
    private ICMonPlayer player;

    private ArrayList <ICMonEvent> currentEvents;

    private ArrayList <ICMonEvent> eventsToRegister;

    private ArrayList <ICMonEvent> eventsToUnRegister;

    private int areaIndex;

    private ICMonGameState gameState = new ICMonGameState();

    private ICMonEventManager eventManager = new ICMonEventManager();

    private void createAreas() {
        addArea(new Town());
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            areaIndex = 0;
            initArea(areas[areaIndex]);
            ICBall ball = new ICBall(getCurrentArea(), new DiscreteCoordinates(6,6),"items/icball");
            currentEvents = new ArrayList<ICMonEvent>();
            eventsToRegister= new ArrayList<ICMonEvent>();
            eventsToUnRegister = new ArrayList<ICMonEvent>();

            CollectItemEvent ballCollect = new CollectItemEvent(ball,player);
            EndOfTheGameEvent endGame = new EndOfTheGameEvent(player);
            eventsToRegister.add(ballCollect);
            eventsToRegister.add(endGame);
            eventsToUnRegister.add(ballCollect);
            eventsToUnRegister.add(endGame);

            ballCollect.onStart(new RegisterEventAction(ballCollect, eventManager));
            ballCollect.onStart(new RegisterinAreaAction(getCurrentArea(),ball));
            ballCollect.onStart(new LogAction("ICMonItemCollect has started !"));
            ballCollect.onComplete(new LogAction("ICMonItemCollect has been completed !"));
            ballCollect.onComplete(new StartEventAction(endGame));
            ballCollect.onComplete(new UnregisterEventAction(ballCollect,eventManager));
            endGame.onStart(new LogAction("the second event has started !"));
            endGame.onStart(new RegisterEventAction(endGame,eventManager));



            ballCollect.start();


//            currentEvents.add(new CollectItemEvent(balle,player));
//            currentEvents.get(0).onStart(new LogAction("CollectItemEvent started !"));
//            currentEvents.get(0).onStart(new RegisterinAreaAction(getCurrentArea(),balle));
//            currentEvents.get(0).onComplete(new LogAction("CollectItemEvent completed !"));
//            currentEvents.get(0).start();
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
        currentEvents.get(0).update(deltaTime);
        eventsToRegister.clear();
        eventsToUnRegister.clear();

        super.update(deltaTime);
    }

    @Override
    public void end() {

    }

    @Override
    public String getTitle() {
        return "ICMon";
    }

    private void initArea(String areaKey) {
        ICMonArea area = (ICMonArea) setCurrentArea(areaKey, true);
        DiscreteCoordinates coords = area.getPlayerSpawnPosition();
        player = new ICMonPlayer(area, Orientation.DOWN, coords, gameState);
        player.enterArea(area, coords);
        player.centerCamera();

    }
    public class ICMonGameState{
        private ICMonGameState(){};

        /**
         * demande à interactable d’accepter de voir ses
         * interactions (avec le personnage) gérée par les événements qui constituent l’état du jeu
         * @param interactable un interactable avec qui le personnage veut interagir, type interactable
         * @param isCellInteraction interaction de contact, type boolean
         */
        public void acceptInteraction (Interactable interactable, boolean isCellInteraction ){
            for(var event : ICMon.this.currentEvents)
                interactable.acceptInteraction(event , isCellInteraction);
        }
    }

    public class ICMonEventManager {

        private ICMonEventManager(){}

        public void registerEvent(ICMonEvent eventToRegister){
            currentEvents.add(eventToRegister);
        }

        public void unRegisterEvent (ICMonEvent eventToUnRegister){
            currentEvents.remove(eventToUnRegister);
        }
    }

//    private void switchArea() {
//        player.leaveArea();
//        areaIndex = (areaIndex == 0) ? 1 : 0;
//        ICMonArea currentArea = (ICMonArea) setCurrentArea(areas[areaIndex], false);
//        player.enterArea(currentArea, currentArea.getPlayerSpawnPosition());
//        player.strengthen();
//    }

}
