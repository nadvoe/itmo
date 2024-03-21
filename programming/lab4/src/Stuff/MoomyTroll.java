package Stuff;

public class MoomyTroll extends Character{
    public MoomyTroll(){
        super.setName("Mуми-Тролль");
        super.setNumber(2);
        super.setCourage((float) Math.random()*9 + 1);
        super.setStrength((float) Math.random()*9 + 1);
        super.setMass((float) Math.random()*9 + 1);
        super.setSize("средний");
        super.setCondition(Conditions.AWAKE.getCondition());
    }
}
