package mypokemons;

import mymoves.Bellsprout.*;
import mymoves.Victreebell.LeafTornado;
import mymoves.Weepinbell.Growth;
import ru.ifmo.se.pokemon.*;

public class Victreebell  extends Pokemon {
    public Victreebell(String name, int level){
        super(name, level);
        super.setType(Type.GRASS, Type.POISON);
        super.setStats(80, 105, 65, 100, 70, 70);

        EnergyBall energyBall = new EnergyBall(90, 100);
        SludgeBomb sludgeBomb = new SludgeBomb(90, 100);
        Growth growth = new Growth(0,0);
        LeafTornado leafTornado = new LeafTornado(65,90);

        super.setMove(energyBall, sludgeBomb, growth, leafTornado);
    }
}

