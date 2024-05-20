package Client;

import CoreClasses.*;

import java.util.Date;

public class ClientManager {

    ReadManager readManager = new ReadManager();

    public Vehicle getVehicle(){
        String name  = readManager.readName();
        Integer x = readManager.readCoordinateX();
        Long y = readManager.readCoordinateY();
        long enginePower = readManager.readEnginePower();
        Double fuelConsumption = readManager.readFuelConsumption();
        VehicleType type = readManager.readVehicleType();
        FuelType fuelType= readManager.readFuelType();
        return new Vehicle ((Long) null, name, new Coordinates(x,y), (Date) null, enginePower, fuelConsumption, type, fuelType);
    }
}
