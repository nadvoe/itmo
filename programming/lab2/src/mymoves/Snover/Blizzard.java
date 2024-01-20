package mymoves.Snover;

import ru.ifmo.se.pokemon.*;
public class Blizzard extends SpecialMove {
    public Blizzard(double pow, double acc) {
        super(Type.ICE, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if (Math.random() < 0.1) {
            Effect.freeze(p);
        }
    }

    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length-1];
    }
}
