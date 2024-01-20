package mypokemons;

import mymoves.Bellsprout.*;
import ru.ifmo.se.pokemon.*;

public class Bellsprout extends Pokemon {
    public Bellsprout(String name, int level){
        super(name, level);
        super.setType(Type.GRASS, Type.POISON);
        super.setStats(50, 75, 35, 70, 30, 40);

        EnergyBall energyBall = new EnergyBall(90,100);
        SludgeBomb sludgeBomb = new SludgeBomb(90,100);

        super.setMove(energyBall,sludgeBomb);
    }
}

