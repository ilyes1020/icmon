package ch.epfl.cs107.icmon.message;

/**
 * Abstract class representing a gameplay message, telling the game to behave or to do specific things
 * Note : Subclasses should implement the process() method to define
 * specific behavior when the message is processed.
 */
public abstract class GamePlayMessage {

    /**
     * Processes the GamePlayMessage
     */
    public abstract void process();
}
