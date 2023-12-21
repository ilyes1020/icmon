package ch.epfl.cs107.icmon.actor.pokemon;

import ch.epfl.cs107.icmon.actor.pokemon.actions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.actions.Meditate;
import ch.epfl.cs107.icmon.actor.pokemon.actions.RunAway;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Bulbizarre extends Pokemon{
    private List<ICMonFightAction> actions;
    private PokemonProperties stats = new PokemonProperties();

    public Bulbizarre(Area area, DiscreteCoordinates position) {
        super(area, position, "bulbizarre", 1, 10);
        actions = new ArrayList<>();
        actions.add(new RunAway());
        actions.add(new Attack(stats.damage()));
        actions.add(new Meditate());
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
