package mymoves.Bellsprout;

import ru.ifmo.se.pokemon.*;

public class EnergyBall extends SpecialMove {
    public EnergyBall(double pow, double acc) {
        super(Type.GRASS, pow, acc);

    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if (Math.random() < 0.1){
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length - 1];
    }
}
