package ch.epfl.cs107.icmon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:        19/11/2023
 */

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public final class ICMon extends AreaGame {
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    //peut ne pas être nécéssaire
    private final String[] areas = {"town", "lab","arena","house","shop"};
    private ICMonPlayer player;
    private List<ICMonEvent> currentEvents;
    private List <ICMonEvent> eventsToRegister;
    private List <ICMonEvent> eventsToUnRegister;
    private ICMonGameState gameState = new ICMonGameState();
    private ICMonEventManager eventManager = new ICMonEventManager();
    private GamePlayMessage currentMessage;

    private void createAreas() {
        addArea(new Town());
        addArea(new Lab());
        addArea(new Arena());
        addArea(new House());
        addArea(new Shop());
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            createAreas();
            initArea("house");
            ICMonItem ball = new ICBall(getCurrentArea(), new DiscreteCoordinates(6,6)); //doit spawn dans l'eau

            currentEvents = new ArrayList<>();
            eventsToRegister= new ArrayList<>();
            eventsToUnRegister = new ArrayList<>();

            events(ball); //que faut-il envoye dans event
            return true;
        }
        return false;
    }

    private void events(ICMonItem ball){
        ICMonEvent firstEvent = new IntroductionEvent(player);
        ICMonEvent talkOak = new FirstInteractionWithProfOakEvent(player);
        ICMonEvent ballCollect = new CollectItemEvent(ball,player);
        ICMonEvent garryInteraction = new FirstInteractionWithGarryEvent(player);
        ICMonEvent endGame = new EndOfTheGameEvent(player);

        ballCollect.onStart(new RegisterinAreaAction(getCurrentArea(),ball));
        ballCollect.onStart(new LogAction("ICMonItemCollect has started !"));
        ballCollect.onComplete(new LogAction("ICMonItemCollect has been completed !"));
        endGame.onStart(new LogAction("the second event has started !"));
        garryInteraction.onStart(new LogAction("Garry interaction began"));


        ICMonChainedEvent chainedEvent= new ICMonChainedEvent(player,firstEvent,talkOak,garryInteraction,ballCollect,endGame);

        chainedEvent.start();
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getCurrentArea().getKeyboard(); //pour reset le jeu
        if (keyboard.get(Keyboard.R).isPressed()){ //isPressed pour pas que ca spam
            begin(getWindow(),getFileSystem());
        }
        if (currentMessage != null) {
            currentMessage.process();
            clearMessage();
        }

        currentEvents.addAll(eventsToRegister);
        currentEvents.removeAll(eventsToUnRegister);

        eventsToRegister.clear();
        eventsToUnRegister.clear();

//        System.out.println(currentEvents);

        for (ICMonEvent event : currentEvents){
            event.update(deltaTime);
        }

        super.update(deltaTime);
    }

    @Override
    public void end() {
    }

    @Override
    public String getTitle() {
        return "ICMon";
    }
    public void clearMessage(){
        currentMessage = null;
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
        public void switchArea(String areaKey, DiscreteCoordinates arrivalPos) {
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(areaKey, false);
            player.enterArea(currentArea, arrivalPos);
        }

        public void pauseTheGame(PauseMenuEvent event){
            for (ICMonEvent currentEvent : currentEvents){
                currentEvent.suspend();
            }
            setPauseMenu(event.getPauseMenu());
            requestPause();
        }

        public void resumeTheGame(){
            requestResume();
            for (ICMonEvent currentEvent : currentEvents){
                currentEvent.resume();
            }
        }

        public void send(GamePlayMessage message){
            ICMon.this.currentMessage = message;
        }

        public ICMonEventManager getEventManager(){
            return eventManager;
        }
    }
    public class ICMonEventManager {

        private ICMonEventManager(){}

        public void toEventsToRegister(ICMonEvent eventToRegister){
            eventsToRegister.add(eventToRegister);
        }

        public void toEventsToUnRegister(ICMonEvent eventToUnRegister){
            eventsToUnRegister.add(eventToUnRegister);
        }
    }
}