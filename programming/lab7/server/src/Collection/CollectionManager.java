package Collection;

import CoreClasses.User;
import CoreClasses.Vehicle;
import CoreClasses.VehicleType;
import JDBC.JDBCManager;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Math.min;
import static java.lang.StrictMath.max;

public class CollectionManager {
    LinkedList<Vehicle> vehicles = new LinkedList<>();
    private final Map<Integer, Vehicle> map = new HashMap<>();
    private long currentId = 1 ;
    private Date date = new Date();
    private String fileName;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public List<User> users = Collections.synchronizedList(new LinkedList<>());
    public static boolean authoriz;
    public static int user_id;
    public static boolean password;

    private final JDBCManager JDBCManager = new JDBCManager();
    long idMax;


    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
    }

    public void setCollection(LinkedList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public LinkedList<Vehicle> getVehicles() {
        return vehicles;
    }

    public String ToString() {
        StringBuilder s = new StringBuilder();
        for (Vehicle vehicle : vehicles) {
            s.append(vehicle.toString());
        }
        return s.toString();
    }

    public List<User> getUsers() {
        return users;
    }


    public void update() {
        Collections.sort(vehicles);
    }

    public String info() {
        return String.format("Collection type: %s\nInitialization date: %s\nSave Time: %s\nThe amount of elements: %d",
                vehicles.getClass().getSimpleName().trim(), lastInitTime.toString(), lastSaveTime, vehicles.size());
    }

    public void show() {
        if (!vehicles.isEmpty()) {
            System.out.println(ToString());
        } else {
            System.out.println("Collection contains no elements.");
        }
    }

    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
        map.put(Math.toIntExact(vehicle.getId()), vehicle);
        update();
    }

    public void updateId(Vehicle newVehicle, long id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                vehicle.setName(newVehicle.getName());
                vehicle.setCoordinates(newVehicle.getCoordinates());
                vehicle.setEnginePower(newVehicle.getEnginePower());
                vehicle.setFuelConsumption(newVehicle.getFuelConsumption());
                vehicle.setType(newVehicle.getType());
                vehicle.setFuelType(newVehicle.getFuelType());
                System.out.println("The element was successfully updated.");
                break;
            }
        }
    }

    public void removeByID(long id) {
        vehicles.removeIf(vehicle -> vehicle.getId() == id);
        System.out.println("The element with id " + id + " was successfully removed.");
    }

    public void clear() {
        vehicles.clear();
    }


    public void exit() {
        System.out.println("The work is finished.");
        System.exit(0);
    }

    public void removeLast() {
        if (!vehicles.isEmpty()) {
            vehicles.removeLast();
        }

    }


    public long getFreeId() {
        while (getVehicleById(currentId++) != null);
        return currentId;
    }

    public Vehicle getVehicleById(long id) {
        return map.get((int) id);
    }

    public double getMinFuelConsumption() {
            double fuelConsumption = Double.MAX_VALUE;
            for (Vehicle a : vehicles) {
                fuelConsumption = Math.min(fuelConsumption, a.getFuelConsumption());
            }
            return fuelConsumption;

    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }
    public LocalDateTime getLastSaveTime() {

        return lastSaveTime;

    }


    public void init() {
        vehicles.clear();
        users.clear();
        JDBCManager.getAllVehicles(this);
        JDBCManager.getAllUsers(this);
        lastInitTime = LocalDateTime.now();
    }

    public boolean isContain(Vehicle vehicle) {

        return vehicle == null || getVehicleById(vehicle.getId()) != null;

    }

    public void insert(Vehicle vehicle) {
            if (!isContain(vehicle)) {
                map.put(Math.toIntExact(vehicle.getId()), vehicle);
                vehicles.add(vehicle);
                update();
            }
    }

    public int regInManager(String user, String password) {
        synchronized (users) {
            idMax = 0;
            for (User user1: users) {
                idMax = max(idMax, user1.getId());
                if ((user1.getName()).equals(user)) {
                    System.out.println(user);
                    authoriz = false;
                    user_id = 1;
                    return -1;
                }
            }
            String ph = generatePh();
            authoriz = true;
            user_id = 1;
            idMax += 1;
            JDBCManager.addUser((int) idMax, user, password, ph);
            users.add(new User((int) idMax, user, password, ph));
            return (int) idMax;
        }
    }


    public static String generatePh() {
        SecureRandom random = new SecureRandom();
        byte[] ph = new byte[16];
        random.nextBytes(ph);
        return Base64.getEncoder().encodeToString(ph);
    }
}