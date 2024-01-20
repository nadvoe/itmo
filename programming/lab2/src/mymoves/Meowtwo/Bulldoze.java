package mymoves.Meowtwo;

import ru.ifmo.se.pokemon.*;

public class Bulldoze extends PhysicalMove {
    public Bulldoze(double pow, double acc) {
        super(Type.GROUND, pow, acc);

    }
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
        Effect effect = new Effect().stat(Stat.SPEED, -1);
        p.addEffect(effect);
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length - 1];
    }
}
