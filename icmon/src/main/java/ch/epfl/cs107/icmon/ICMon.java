package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.message.GamePlayMessage;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public final class ICMon extends AreaGame {
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private ICMonPlayer player;
    private List<ICMonEvent> currentEvents;
    private List <ICMonEvent> eventsToRegister;
    private List <ICMonEvent> eventsToUnRegister;
    private ICMonGameState gameState = new ICMonGameState();
    private ICMonEventManager eventManager = new ICMonEventManager();
    private GamePlayMessage currentMessage;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Creates areas
            Town town = new Town();
            addArea(town);
            addArea(new Lab());
            addArea(new Arena());
            addArea(new House());
            addArea(new Shop());

            // Initializes the first area
            initArea("house");

            // Initializes the event lists
            currentEvents = new ArrayList<>();
            eventsToRegister= new ArrayList<>();
            eventsToUnRegister = new ArrayList<>();
            new TitleScreenEvent(player).start();
            events(town);
            return true;
        }
        return false;
    }

    /**
     * Configures and initiates the first sequence of events.
     *
     * @param area   The ICMonArea in which the CollectItemEvent will take place.
     */
    private void events(ICMonArea area){
        ICMonItem ball = new ICBall(area, new DiscreteCoordinates(6,6), "icball");

        ICMonEvent firstEvent = new IntroductionEvent(player);
        ICMonEvent talkOak = new FirstInteractionWithProfOakEvent(player);
        ICMonEvent ballCollect = new CollectItemEvent(ball,player,area);
        ICMonEvent garryInteraction = new FirstInteractionWithGarryEvent(player);
        ICMonEvent endGame = new EndOfTheGameEvent(player);

        ICMonChainedEvent chainedEvent= new ICMonChainedEvent(player,firstEvent,talkOak,garryInteraction,ballCollect,endGame);

        chainedEvent.start();
    }

    @Override
    public void update(float deltaTime) {
        // Restart the game
        Keyboard keyboard = getCurrentArea().getKeyboard();
        if (keyboard.get(Keyboard.R).isPressed()) {
            begin(getWindow(), getFileSystem());
        }

        // Processes the current game play message and clears it afterward.
        if (currentMessage != null) {
            currentMessage.process();
            clearMessage();
        }

        // Manage events:
        currentEvents.addAll(eventsToRegister);
        currentEvents.removeAll(eventsToUnRegister);
        eventsToRegister.clear();
        eventsToUnRegister.clear();


        // Iterates through the list of current events and updates each event.
        for (ICMonEvent event : currentEvents) {
            event.update(deltaTime);
        }
        super.update(deltaTime);
    }

    @Override
    public String getTitle() {
        return "ICMon";
    }

    /**
     * Clears the current message.
     */
    public void clearMessage(){
        currentMessage = null;
    }

    /**
     * Initializes the specified game area.
     *
     * @param areaKey   The key identifying the game area to be initialized.
     */
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
         * Asks the interactable to accept the handling of its interactions (with the character) by the events
         * that constitute the game state.
         *
         * @param interactable         An interactable with which the character wants to interact, of type Interactable.
         * @param isCellInteraction    A boolean indicating whether it is a contact-based interaction.
         */
        public void acceptInteraction (Interactable interactable, boolean isCellInteraction ){
            for(var event : ICMon.this.currentEvents)
                interactable.acceptInteraction(event , isCellInteraction);
        }

        /**
         * Switches the player to a new game area.
         *
         * @param areaKey       The key identifying the target game area.
         * @param arrivalPos    The position where the player should arrive in the new area.
         */
        public void switchArea(String areaKey, DiscreteCoordinates arrivalPos) {
            player.leaveArea();
            ICMonArea currentArea = (ICMonArea) setCurrentArea(areaKey, false);
            player.enterArea(currentArea, arrivalPos);
            currentArea.setAreaMusic(getWindow());
        }

        /**
         * Sends a game play message to be stored as the current message in the game.
         *
         * @param message   The GamePlayMessage to be stored.
         */
        public void send(GamePlayMessage message){
            ICMon.this.currentMessage = message;
        }

        public ICMonEventManager getEventManager(){
            return eventManager;
        }

    }
    public class ICMonEventManager {

        private ICMonEventManager(){}

        /**
         * Adds an event to the list of events to register.
         *
         * @param eventToRegister   The ICMonEvent to be registered.
         */
        public void toEventsToRegister(ICMonEvent eventToRegister){
            eventsToRegister.add(eventToRegister);
        }

        /**
         * Adds an event to the list of events to unregister.
         *
         * @param eventToUnRegister   The ICMonEvent to be unregistered.
         */
        public void toEventsToUnRegister(ICMonEvent eventToUnRegister){
            eventsToUnRegister.add(eventToUnRegister);
        }
        /**
         * Resumes the game, activating all suspended events and ending the pause state.
         */
        /**
         * Pauses the game, suspending all current events and activating a specified pause menu event.
         *
         * @param event   The PauseMenuEvent to be activated during the pause.
         */
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
            ((ICMonArea) getCurrentArea()).setAreaMusic(getWindow());
        }

        /**
         * In order to restart the game from an event
         */
        public void restartTheGame() {
            begin(getWindow(),getFileSystem());
        }
    }
}