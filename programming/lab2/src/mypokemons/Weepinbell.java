package mypokemons;

import mymoves.Bellsprout.*;
import mymoves.Weepinbell.Growth;
import ru.ifmo.se.pokemon.*;

public class Weepinbell extends Pokemon {
    public Weepinbell(String name, int level) {
        super(name, level);
        super.setType(Type.GRASS, Type.POISON);
        super.setStats(65, 90, 50, 85, 45, 55);

        EnergyBall energyBall = new EnergyBall(90, 100);
        SludgeBomb sludgeBomb = new SludgeBomb(90, 100);
        Growth growth = new Growth(0,0);

        super.setMove(energyBall, sludgeBomb,growth);
    }
}

