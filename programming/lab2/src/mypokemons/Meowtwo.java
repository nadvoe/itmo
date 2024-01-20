package mypokemons;
import mymoves.Meowtwo.*;
import ru.ifmo.se.pokemon.*;

public class Meowtwo extends Pokemon {
    public Meowtwo(String name, int level){
        super(name, level);
        super.setType(Type.PSYCHIC);
        super.setStats(106, 110, 90, 154, 90, 130);

        Confusion confusion = new Confusion(50, 100);
        Bulldoze bulldoze = new Bulldoze(60, 100);
        Recover recover = new Recover(0,0);
        AerialAce aerialAce = new AerialAce(60, Double.POSITIVE_INFINITY);
        super.setMove(confusion, bulldoze, recover, aerialAce);
    }
}
