package Stuff;

public class Sea extends Ocean {
    public Sea() {
        super.setName("Море Муми-долины");
        super.setDeep(2000);
        super.setNumber(1);
        super.setWaves((float) Math.random() * 6 + 1);
    }
}

