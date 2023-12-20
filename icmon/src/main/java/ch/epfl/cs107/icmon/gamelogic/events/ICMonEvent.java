package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnregisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an event in game
 * Implements the Updatable and the ICMonInteractionVisitor interface
 */
public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {
    protected ICMonPlayer player;
    private boolean started;
    private boolean completed;
    private boolean suspended;
    private List<Action> ActionsOnStart = new ArrayList<Action>();
    private List<Action> ActionsOnComplete = new ArrayList<Action>();
    private List<Action> ActionsOnSuspend = new ArrayList<Action>();
    private List<Action> ActionsOnResume = new ArrayList<Action>();

    /**
     * Constructor of an ICMonEvent
     *
     * @param player The player
     */
    public ICMonEvent(ICMonPlayer player){
        started = false;
        completed = false;
        suspended = false;
        this.player = player;
        onStart(new RegisterEventAction(this, player.getEventManager()));
        onComplete(new UnregisterEventAction(this, player.getEventManager()));
    }

    /**
     * execute actions when the event is started and mark the event as completed
     */
    public final void start(){
        if (!started){
            for (Action action : ActionsOnStart){
                action.perform();
            }
            started = true;
        }
    }
    /**
     * execute actions when the event is completed and mark the event as completed
     */
    public final void complete(){
        if (started && !completed){
            for (Action action : ActionsOnComplete){
                action.perform();
            }
            completed = true;
        }
    }
    /**
     *  begins the actions when suspended, suspends the event and marks it as suspended
     */
    public final void suspend(){
        if (started && !completed && !suspended){
            for (Action action : ActionsOnSuspend){
                action.perform();
            }
            suspended= true;
        }
    }

    /**
     * begins the actions when resumed, resumes the event and marks it as unsuspended
     */
    public final void resume(){
        if (started && !completed && suspended){
            for (Action action : ActionsOnResume){
                action.perform();
            }
            suspended = false;
        }
    }
    /**
     * Add action to the list of actions to do when the event is started
     * @param action action to add, type Action
     */
    public final void onStart(Action action){
        ActionsOnStart.add(action);
    }
    /**
     * Add action to the list of actions to do when the event is completed
     * @param action action to add, type Action
     */
    public final void onComplete(Action action){
        ActionsOnComplete.add(action);
    }
    /**
     * Add action to the list of actions to do when the event is suspended
     * @param action action to add, type Action
     */
    public final void onSuspention(Action action){
        ActionsOnSuspend.add(action);
    }

    /**
     * Add action to the list of actions to do when the event is resumed
     * @param action action to add, type Action
     */
    public final void onResume(Action action){
        ActionsOnResume.add(action);
    }

    public final boolean isStarted() {
        return started;
    }

    public final boolean isCompleted() {
        return completed;
    }

    public final boolean isSuspended() {
        return suspended;
    }

    /**
     * Simulates a single time step.
     * Note: Need to be Override
     *
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
    }
}
