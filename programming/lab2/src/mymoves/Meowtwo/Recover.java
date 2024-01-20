package mymoves.Meowtwo;

import ru.ifmo.se.pokemon.*;

public class Recover extends StatusMove  {
    public Recover (double pow, double acc){
        super(Type.NORMAL, pow, acc);

    }

    @Override
    protected void applySelfEffects (Pokemon p){
        super.applySelfEffects(p);
        Effect effect = new Effect().stat(Stat.HP, (int) (0.5 * Math.round(p.getStat(Stat.HP))));
        p.addEffect(effect);
    }


    @Override
    protected String describe(){
        String[] pieces = this.getClass().toString().split("\\.");
        return "использует " + pieces[pieces.length-1];
    }
}


