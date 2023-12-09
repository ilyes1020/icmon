package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Ilyes Rouibi
 *	Date:
 */

import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lab extends ICMonArea {
    @Override
    public String getTitle() {
        return "lab";
    }

    @Override
    protected void createArea() {

    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return null;
    }
}
