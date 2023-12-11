package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.player.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.PauseMenu;

public class PokemonFightEvent extends ICMonEvent implements PauseMenuEvent {

    private ICMonFight pauseMenu;
    public PokemonFightEvent(ICMonPlayer player, ICMonFightableActor ennemy){
        super(player);
        pauseMenu= new ICMonFight();
        onComplete(new LeaveAreaAction((ICMonActor) ennemy));
    }
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    @Override
    public void update(float deltaTime) { //pas sûr ici sur cette redefinition
        if (!pauseMenu.isRunning()){
            complete();
        }
    }
}
