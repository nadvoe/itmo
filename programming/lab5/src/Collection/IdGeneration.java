package Collection;
import CoreClasses.Vehicle;

import java.util.LinkedList;

public class IdGeneration {

    private static LinkedList<Long> idList = new LinkedList<>();


    public static long generateID(){
        long id = (long) System.currentTimeMillis();
        while (idList.contains(id)){
            id = (long) System.currentTimeMillis();
        }
        idList.add(id);
        return id;
    }

    public static void addId(Vehicle vehicle){
        idList.add(vehicle.getId());
    }

    public LinkedList<Long> getIdList (){
        return idList;
    }

}
