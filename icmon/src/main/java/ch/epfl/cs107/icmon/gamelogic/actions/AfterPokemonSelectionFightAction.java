package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEventMessage;

/**
 * Action to initialize a pokémon fight event right after the pokémon selection
 */
public class AfterPokemonSelectionFightAction implements Action{
    private ICMon.ICMonGameState gameState;
    private ICMonPlayer player;
    private Pokemon playersPokemon;
    private ICMonFightableActor opponent;
    public AfterPokemonSelectionFightAction(ICMonPlayer player, Pokemon playersPokemon, ICMonFightableActor opponent, ICMon.ICMonGameState gameState){
        this.player=player;
        this.playersPokemon=playersPokemon;
        this.opponent=opponent;
        this.gameState =gameState;
    }
    @Override
    public void perform() {
        ICMonEvent fightEvent = new PokemonFightEvent(player, playersPokemon, opponent);
        gameState.send(new SuspendWithEventMessage(fightEvent,gameState));
    }
}
