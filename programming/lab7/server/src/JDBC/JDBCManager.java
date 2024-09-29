package JDBC;

import Collection.CollectionManager;
import CoreClasses.*;
import Server.TCPServer;

import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDateTime;

public class JDBCManager {
    public static void deleteVehicleById(long id) {
        String select = "DELETE FROM Vehicle WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setLong(1, id);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteVehicleByCreatorId(long creatorId) {
        String select = "DELETE FROM Vehicle WHERE creatorId = ?";

        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setLong(1, creatorId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addVehicle(Vehicle vehicle) {
        String select = "INSERT INTO Vehicle (id, name, coordinates_x, coordinates_y, creationDate, enginePower, fuelConsumption, vehicleType, fuelType,  creatorId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setLong(1, vehicle.getId());
            statement.setString(2, vehicle.getName());
            statement.setInt(3, vehicle.getCoordinates().getX());
            statement.setInt(4, Math.toIntExact(vehicle.getCoordinates().getY()));
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(vehicle.getCreationDate()));
            statement.setLong(6, vehicle.getEnginePower());
            statement.setDouble(7, vehicle.getFuelConsumption());
            statement.setString(8, String.valueOf(vehicle.getType()));
            statement.setString(9, String.valueOf(vehicle.getFuelType()));
            statement.setInt(10, vehicle.getCreatorId());

            statement.executeUpdate();

        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // SQLState for unique constraint violation
                System.out.println("Error: marchrute with such id already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }


    public static void updateVehicleById(Vehicle vehicle) {
        String select = "UPDATE Vehicle SET id = ?, name = ?, coordinates_x = ?, coordinates_y = ?, creationDate = ?,  enginePower = ?, fuelConsumption = ?, vehicleType = ?, fuelType = ?, creatorId = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select)) {

            statement.setLong(1, vehicle.getId());
            statement.setString(2, vehicle.getName());
            statement.setInt(3, vehicle.getCoordinates().getX());
            statement.setLong(4, vehicle.getCoordinates().getY());
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(vehicle.getCreationDate()));
            statement.setLong(6, vehicle.getEnginePower());
            statement.setDouble(7, vehicle.getFuelConsumption());
            statement.setString(8, String.valueOf(vehicle.getType()));
            statement.setString(9, String.valueOf(vehicle.getFuelType()));
            statement.setLong(13, vehicle.getCreatorId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllVehicles(CollectionManager collectionManager) {
        String select = "SELECT * FROM Vehicle";


        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                int creatorId = resultSet.getInt("creatorId");
                String name = resultSet.getString("name");
                int coordinates_x = resultSet.getInt("coordinates_x");
                long coordinates_y = resultSet.getLong("coordinates_y");
                LocalDateTime creationDate = resultSet.getTimestamp("creationDate").toLocalDateTime();
                long enginePower = resultSet.getLong("enginePower");
                double fuelConsumption = resultSet.getDouble("fuelConsumption");
                VehicleType vehicleType = VehicleType.valueOf(resultSet.getString("vehicleType"));
                FuelType fuelType = FuelType.valueOf(resultSet.getString("fuelType"));

                Coordinates coordinates = new Coordinates( coordinates_x,  coordinates_y);

                Vehicle vehicle = new Vehicle(id, name, coordinates, creationDate, enginePower, fuelConsumption, vehicleType, fuelType, creatorId);
                collectionManager.insert(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllUsers(CollectionManager collectionManager) {
        String select = "SELECT * FROM users";


        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(select);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String psw = resultSet.getString("password");

                User user = new User(id, name, psw, null);
                collectionManager.getUsers().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addUser(int id, String name, String password, String salt) {
        String query = "INSERT INTO Users (id, name, password, ph) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            String psw = hashPassword(password, salt);
            statement.setString(3, psw);

            statement.setString(4, salt);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int authenticateUser(String name, String password) throws SQLException {

        String selectUserSQL = "SELECT id, password, ph FROM Users WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(Conf.url, Conf.creator, Conf.pass);
             PreparedStatement statement = connection.prepareStatement(selectUserSQL)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String salt = resultSet.getString("ph");
                    String expectedHashedPassword = resultSet.getString("password");
                    String actualHashedPassword = hashPassword(password, salt);
                    if (expectedHashedPassword.equals(actualHashedPassword)) {
                        return id;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
    }

    public static String hashPassword(String password, String salt) {
        password += salt;
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD2");

            byte[] hash = digest.digest(password.getBytes());
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        } catch (Exception e) {
            System.out.println("password problem eklmn");
        }
        return hexString.toString();
    }
}
