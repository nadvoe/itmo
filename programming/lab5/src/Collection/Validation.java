package Collection;

import CoreClasses.Vehicle;

public class Validation {
    private final IdGeneration idGeneration = new IdGeneration();
    public IdGeneration getIdGeneration(){return idGeneration;}
    public Validation(){}

    public Vehicle getValidatedModel (Vehicle vehicle){
        if (vehicle.getId() < 0 || vehicle.getName() == null || vehicle.getName().isBlank() || vehicle.getCoordinates() == null || vehicle.getCreationDate() == null || vehicle.getEnginePower() < 0 || vehicle.getFuelConsumption() < 0){
            return null;
        }else{
            if (vehicle.getId() == 0){
                vehicle.setId(IdGeneration.generateID());
            }
            if (vehicle.getCreationDate() == null){
                vehicle.setCreationDate();
            }
            return vehicle;
        }
    }
}
