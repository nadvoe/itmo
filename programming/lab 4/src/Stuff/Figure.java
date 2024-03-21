package Stuff;

public abstract class Figure {
    private String name;
    private int number ;
    private String condition;
    private String size;
    private int mass;


    public Figure(){
        this.name = "character object";
        this.number = 0;
        this.condition = "";
        this.size = "";
        this.mass =0;
    }
    //геттеры
    public final String getName(){
        return this.name;
    }
    public final int getNumber(){
        return this.number;
    }

    public final int getMass(){
        return this.mass;
    }

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
    public final void setMass(int x){
        this.number = x;
    }

    public final void setCondition(String c){
        this.condition = c;
    }
    public final void setSize(String g){
        this.size = g;
    }

    public boolean isBeautiful() {
        if (Math.random() > 0.5){
            return true;
        } else{
            return false;
        }
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
        Figure figure = (Figure) o;
        return number == figure.number;
    }
}