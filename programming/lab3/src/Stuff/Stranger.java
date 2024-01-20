package Stuff;

public class Stranger extends Figure implements Buoyancy {
    public Stranger(){
        super.setName("Деревянная фигура женщины");
        super.setNumber(2);
        super.setSize("большая");
        super.setCondition(Conditions.SLEEPING.getCondition());
    }
    @Override
    public String swim(){
        return " не тонет";
    }



}
