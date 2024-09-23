package CoreClasses;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; // x <= 5, can't be null
    private Long y; //can't be null

    public Coordinates(Integer x, Long y){
        this.x = x;
        this.y = y;
    }

    public Coordinates(String s) {
        try {
            try { this.x = Integer.parseInt(s.split(";")[0]); } catch (NumberFormatException e) { }
            try { this.y = Long.parseLong(s.split(";")[1]); } catch (NumberFormatException e) { }
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    public Integer getX(){
        return this.x;
    }
    public Long getY(){
        return this.y;
    }

    public void setX(Integer x){
        this.x = x;
    }
    public void setY(Long y){
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

}
