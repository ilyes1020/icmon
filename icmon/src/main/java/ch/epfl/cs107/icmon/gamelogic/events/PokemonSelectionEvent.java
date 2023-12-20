package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;
import java.util.List;

/**
 * Event for selecting Pokémon before a fight
 * Implements the PauseMenuEvent interface
 */
public class PokemonSelectionEvent extends ICMonEvent implements PauseMenuEvent {
    private PokemonSelectionMenu pauseMenu;
    private ICMonFightableActor opponent;
    private ICMon.ICMonGameState gameState;

    /**
     * Constructor of a PokemonSelectionEvent.
     *
     * @param player            The player
     * @param playersPokemonList The player's list of Pokémon available for selection
     * @param opponent          The opponent Pokémon in the upcoming fight
     * @param gameState         The game state for handling the Pokémon selection event
     */
    public PokemonSelectionEvent(ICMonPlayer player, List<Pokemon> playersPokemonList, ICMonFightableActor opponent, ICMon.ICMonGameState gameState){
        super(player);
        pauseMenu = new PokemonSelectionMenu(playersPokemonList);
        this.opponent=opponent;
        this.gameState=gameState;
        onStart(new SuspendEventAction(this, player.getEventManager()));

    }
    @Override
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void pauseMessage() {
        System.out.println("suspension des événements en cours à cause d'un événement séléction");
    }

    /**
     * Updates the event over time and performs actions after Pokémon selection and complete the event
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        if (!pauseMenu.isRunning()){
            onComplete(new AfterPokemonSelectionFightAction(player, pauseMenu.getChosenPokemon(), opponent, gameState));
            complete();
        }
    }
}