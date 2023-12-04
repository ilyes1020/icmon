package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Entity;

import java.util.ArrayList;

public class ICMonEvent implements Updatable {

    private boolean started;
    private boolean completed;
    private boolean suspended;
    private ArrayList<Action> ActionsOnStart = new ArrayList<Action>();
    private ArrayList<Action> ActionsOnComplete = new ArrayList<Action>();
    private ArrayList<Action> ActionsOnSuspend = new ArrayList<Action>();
    private ArrayList<Action> ActionsOnResume = new ArrayList<Action>();

    public ICMonEvent(){
        started = false;
        completed = false;
        suspended = false;
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
            for (Action action : ActionsOnStart){
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
