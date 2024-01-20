package Stuff;

public abstract class Character {
    private String name;
    private int number ;
    private int courage;
    private int strength;
    private int mass;
    private String condition;
    private String size;



    public Character(){
        this.name = "character object";
        this.number = 0;
        this.courage = 0;
        this.strength =0;
        this.mass = 0;
        this.condition = "";
        this.size = "";
    }
    //геттеры
    public final String getName(){
        return this.name;
    }
    public final int getNumber(){
        return this.number;
    }

    public final int getCourage(){return this.courage;}
    public final int getStrength(){return this.strength;}
    public final int getMass(){return this.mass;}

    public final String getCondition(){
        return this.condition;
    }
    public final String getSize(){
        return this.size;
    }

    //сеттеры
    public final void setName(String n){
        this.name = n;
    }
    public final void setNumber(int x){
        this.number = x;
    }
    public final void setCourage (int x) {this.courage = x;}
    public final void setStrength (int x) {this.strength = x;}
    public final void setMass(int x){this.mass = x;}
    public final void changeStrength(int x){this.strength += x;}
    public final void setCondition(String c){
        this.condition = c;
    }
    public final void setSize(String g){
        this.size = g;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return this.number;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return number == character.number;
    }
}
