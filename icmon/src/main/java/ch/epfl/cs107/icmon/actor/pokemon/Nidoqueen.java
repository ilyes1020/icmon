package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

public class Nidoqueen extends Pokemon{
    private List<ICMonFightAction> actions;
    private PokemonProperties stats = new PokemonProperties();

    public Nidoqueen(Area area, DiscreteCoordinates position) {
        super(area, position, "nidoqueen", 2, 15);
        actions = new ArrayList<>();
        actions.add(new RunAway());
        actions.add(new Attack(stats.damage()));
    }

    @Override
    public ArrayList<ICMonFightAction> getActions() {
        return new ArrayList<>(actions);
    }
}
