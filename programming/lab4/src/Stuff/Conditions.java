package Stuff;

public enum Conditions {
    AWAKE("бодрствует"),
    SLEEPING("cпит");
    private String condition;

    Conditions(String condition){
        this.condition = condition;
    }
    public String getCondition(){
        return condition;
    }

}
