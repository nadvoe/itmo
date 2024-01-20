package Stuff;

public class FrekenSnork extends Character implements Shivering {
    public FrekenSnork(){
        super.setName("Фрекен Снорк");
        super.setNumber(1);
        super.setCourage((int) Math.random()*9 + 1);
        super.setStrength((int) Math.random()*9 + 1);
        super.setMass((int) Math.random()*9 + 1);
        super.setSize("маленькая");
        super.setCondition(Conditions.AWAKE.getCondition());
    }
    @Override
    public String shiver(){
        return " дрожит ";
    }
}
