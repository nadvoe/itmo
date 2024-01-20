package mymoves.Bellsprout;

import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {

    public SludgeBomb(double pow, double acc){
        super(Type.POISON, pow, acc);

    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        if (Math.random() < 0.3){
            Effect.poison(p);
        }
    }

    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length-1];
    }
}
