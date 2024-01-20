package mypokemons;

import mymoves.Snover.*;
import ru.ifmo.se.pokemon.*;

public class Snover extends Pokemon {
    public Snover(String name, int level){
        super(name, level);
        super.setType(Type.GRASS.ICE);
        super.setStats(60, 62, 50, 62, 60, 40);

        IceBeam iceBeam = new IceBeam(90, 100);
        Blizzard blizzard = new Blizzard(110,70);
        ShadowBall shadowBall = new ShadowBall(80,110);

        super.setMove(iceBeam, blizzard, shadowBall);
    }
}





