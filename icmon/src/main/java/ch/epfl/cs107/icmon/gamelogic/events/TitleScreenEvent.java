package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.titlescreen.ICMonTitleScreen;
import ch.epfl.cs107.play.engine.PauseMenu;
public class TitleScreenEvent extends ICMonEvent implements PauseMenuEvent{

    private ICMonTitleScreen pauseMenu;

    /**
     * Constructor of a PokemonSelectionEvent.
     *
     * @param player The player
     */
    public TitleScreenEvent(ICMonPlayer player){
        super(player);
        pauseMenu = new ICMonTitleScreen();
        onStart(new SuspendEventAction(this, player.getEventManager()));
        onComplete(new ResumeEventAction(this, player.getEventManager()));
    }
    @Override
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void pauseMessage() {
        System.out.println("main menu");
    }

    /**
     * Updates the event over time and performs actions after Pokémon selection and complete the event
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        if (!pauseMenu.isRunning()){
            complete();
        }
    }
}
