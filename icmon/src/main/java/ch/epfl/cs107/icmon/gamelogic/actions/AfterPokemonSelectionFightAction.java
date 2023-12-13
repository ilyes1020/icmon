package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.message.SuspendWithEvent;

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
        ICMonEvent fightEvent = new PokemonFightEvent(player,playersPokemon,opponent);
        fightEvent.onStart(new RegisterEventAction(fightEvent,gameState.getEventManager()));
        fightEvent.onComplete(new LeaveAreaAction((ICMonActor) opponent));
        fightEvent.onComplete(new UnregisterEventAction(fightEvent, gameState.getEventManager()));
        SuspendWithEvent fightMessage = new SuspendWithEvent(fightEvent,gameState);
        gameState.send(fightMessage);
    }
}
