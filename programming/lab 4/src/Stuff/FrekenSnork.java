package Stuff;

public class FrekenSnork extends Character implements Shivering {
    public FrekenSnork(){
        super.setName("Фрекен Снорк");
        super.setNumber(1);
        super.setCourage((float) Math.random()*9 + 1);
        super.setStrength((float) Math.random()*9 + 1);
        super.setMass((float) Math.random()*9 + 1);
        super.setSize("маленькая");
        super.setCondition(Conditions.AWAKE.getCondition());
    }

    //вложенный non-static class
    public class Bangs {
        private String condition;
        public Bangs (){
            this.condition = "condition";
        }
        public final String getCondition(){ return this.condition;}
        public final void setCondition(String x){this.condition = x;}
    }



    @Override
    public String shiver(){
        return " дрожит ";
    }
}
