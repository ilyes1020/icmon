package ch.epfl.cs107.icmon.actor.pokemon;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import java.util.List;

public interface ICMonFightableActor{
    public boolean hasToLeaveArea();
    public List<Pokemon> getPokemons();
}
