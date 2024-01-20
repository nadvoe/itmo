package mymoves.Victreebell;

import ru.ifmo.se.pokemon.*;

public class LeafTornado extends SpecialMove {
    public LeafTornado (double pow, double acc){
        super(Type.GRASS, pow, acc);

    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if (Math.random() < 0.3) {
            Effect e = new Effect().stat(Stat.ACCURACY, -1);
            p.addEffect(e);
        }
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length-1];
    }

}
