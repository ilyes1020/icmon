package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.PauseMenu;

/**
 * Event for a Pokémon fight
 * Implements the PauseMenuEvent interface
 */
public class PokemonFightEvent extends ICMonEvent implements PauseMenuEvent {

    private ICMonFight pauseMenu;
    private ICMonFightableActor opponent;
    private boolean hasAlreadyLeft;

    /**
     * Constructor of a PokemonFightEvent.
     *
     * @param player              The player involved in the event
     * @param playersPokemon      The Pokémon controlled by the player
     * @param opponent            The opponent Pokémon in the fight
     */
    public PokemonFightEvent(ICMonPlayer player, Pokemon playersPokemon, ICMonFightableActor opponent){
        super(player);
        pauseMenu = new ICMonFight(playersPokemon,opponent.getPokemons().get(0), player.getNbBerry());
        this.opponent=opponent;
        onStart(new SuspendEventAction(this, player.getEventManager()));
        onComplete(new ResumeEventAction(this, player.getEventManager()));
    }

    @Override
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void pauseMessage() {
        System.out.println("suspension des événements en cours à cause d'un événement de combat");
    }

    /**
     * Updates the event over time.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        if (opponent.hasToLeaveArea() && !hasAlreadyLeft){
            onComplete(new LeaveAreaAction((ICMonActor) opponent));
            hasAlreadyLeft = true;
        }
        if (!pauseMenu.isRunning()){
            complete();
        }
    }
}
