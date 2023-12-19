package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PokemonFightEvent extends ICMonEvent implements PauseMenuEvent {

    private ICMonFight pauseMenu;
    private ICMonFightableActor opponent;
    private boolean hasAlreadyLeft;
    public PokemonFightEvent(ICMonPlayer player, Pokemon playersPokemon, ICMonFightableActor opponent){
        super(player);
        pauseMenu = new ICMonFight(playersPokemon,opponent.getPokemons().get(0));
        this.opponent=opponent;
    }
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void update(float deltaTime) { //pas sûr ici sur cette redefinition
        if (opponent.hasToLeaveArea() && !hasAlreadyLeft){
            onComplete(new LeaveAreaAction((ICMonActor) opponent));
            hasAlreadyLeft = true;
        }
        if (!pauseMenu.isRunning()){
            complete();
        }
    }
}
