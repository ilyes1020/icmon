package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Meditate;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Roost;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;

public class Latios extends Pokemon{
    private List<ICMonFightAction> actions;
    private PokemonProperties stats = new PokemonProperties();

    public Latios(Area area, DiscreteCoordinates position) {
        super(area, position, "latios", 4, 12);
        actions = new ArrayList<>();
        actions.add(new RunAway());
        actions.add(new Attack(stats.damage()));
        actions.add(new Roost());
    }

    @Override
    public ArrayList<ICMonFightAction> getActions() {
        return new ArrayList<>(actions);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
