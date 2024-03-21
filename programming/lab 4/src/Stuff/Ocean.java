package Stuff;

public abstract class Ocean {
    private String name;
    private int deep;
    private int number;
    private static float waves;  // static для нашего static inner class

    public Ocean(){
        this.name = "sea object";
        this.deep = 0;
        this.number = 0;
        this.waves = 0;
    }
    //static inner class с доступом к static полю waves
    public static class Storm {
        public static float Stormy(){
            return waves;
        }
    }

    public final String getName(String n){return this.name;}
    public final int getDeep(int x){return this.deep;}
    public final float getWaves(){return this.waves;}
    public final int getNumber(int x){return this.number;}

    public final void setName(String n){this.name = n;}
    public final void setDeep(int x){this.deep = x;}
    public final void setWaves(float x){ this.waves = x;}
    public final void setNumber(int x){this.number = x;}

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
        Ocean ocean = (Ocean) o;
        return number == ocean.number;
    }


}
