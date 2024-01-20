package mymoves.Meowtwo;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class AerialAce extends PhysicalMove {
    public AerialAce(double pow, double acc){
        super(Type.FLYING, pow, acc);
    }
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }


    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length-1];
    }
}
