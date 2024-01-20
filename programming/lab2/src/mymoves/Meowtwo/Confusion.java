package mymoves.Meowtwo;

import ru.ifmo.se.pokemon.*;

public class Confusion extends SpecialMove {
    public Confusion(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (Math.random() < 0.9){
            Effect.confuse(p);
        }
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length - 1];
    }
}
