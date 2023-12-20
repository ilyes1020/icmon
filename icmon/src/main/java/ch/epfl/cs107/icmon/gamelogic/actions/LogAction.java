package ch.epfl.cs107.icmon.gamelogic.actions;

/**
 * Action to print a log on the console
 */
public class LogAction implements Action{
    private String message;

    public LogAction(String message){
        this.message = message;
    }

    @Override
    public void perform() {
        System.out.println(message);
    }
}
