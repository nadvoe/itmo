package mypokemons;

import mymoves.Abomasnow.RockSlide;
import mymoves.Snover.*;
import ru.ifmo.se.pokemon.*;

public class Abomasnow extends Pokemon {
    public Abomasnow(String name, int level) {
        super(name, level);
        super.setType(Type.GRASS, Type.ICE);
        super.setStats(90, 92, 75, 92, 85, 60);

        IceBeam iceBeam = new IceBeam(90, 100);
        Blizzard blizzard = new Blizzard(110,70);
        ShadowBall shadowBall = new ShadowBall(80,110);
        RockSlide rockSlide = new RockSlide(75, 90);

        super.setMove(iceBeam, blizzard, shadowBall, rockSlide);
    }
}
