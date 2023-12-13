package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnregisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.play.engine.PauseMenu;

import java.util.ArrayList;

public class PokemonSelectionEvent extends ICMonEvent implements PauseMenuEvent {

    private PokemonSelectionMenu pauseMenu;

    private ICMonFightableActor opponent;

    private ICMon.ICMonGameState gameState;
    public PokemonSelectionEvent(ICMonPlayer player, ArrayList<Pokemon> playersPokemonList, ICMonFightableActor opponent, ICMon.ICMonGameState gameState){
        super(player);
        pauseMenu = new PokemonSelectionMenu(playersPokemonList);
        this.opponent=opponent;
        this.gameState=gameState;

    }
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void update(float deltaTime) { //pas sûr ici sur cette redefinition
        if (!pauseMenu.isRunning()){
            onComplete(new AfterPokemonSelectionFightAction(player, pauseMenu.getPokemonChosen(), opponent, gameState));
            complete();
        }
    }
}
