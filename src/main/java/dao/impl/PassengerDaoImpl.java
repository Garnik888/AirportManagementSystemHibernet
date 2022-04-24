package dao.impl;

import dao.PassengerDao;
import model.Address;
import model.Passenger;
import service.DatabaseConnectionService;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {
    @Override
    public void createPassenger(Passenger passenger) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Passenger (pass_name, pass_phone,address_id)" +
                        " VALUES ('" +
                        passenger.getName() + "', '" +
                        passenger.getPhone() + "', '" +
                        passenger.getIdAddress() + "');";

        Statement statement = null;
        try {
            assert connection != null;
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(long id, Passenger passenger) {
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Passenger " +
                                         "SET pass_name = ?," +
                                         "pass_phone = ?," +
                                         "address_id = ? " +
                                         "WHERE id = ?"
                         )
            ) {
                preparedStatement.setString(1, passenger.getName());
                preparedStatement.setString(2, passenger.getPhone());
                preparedStatement.setLong(3, passenger.getIdAddress());
                preparedStatement.setLong(4, id);

                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        try {
            assert connection != null;
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Passenger WHERE id = " + id + ";";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Passenger getPassengerById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Passenger passenger = null;
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Passenger WHERE id = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passenger = new Passenger(
                        resultSet.getLong("id"),
                        resultSet.getLong("address_id"),
                        resultSet.getString("pass_name"),
                        resultSet.getString("pass_phone")

                );
            }

        } catch (SQLException e) {
            System.out.println("Wrong query for Passenger with id=" + id);
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection cannot close");
            }
        }

        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        Set<Passenger> passengeres = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(
                                 "SELECT * FROM Passenger"
                         )
            ) {
                passengeres = new HashSet<>();

                Passenger passenger;
                while (resultSet.next()) {
                    passenger= new Passenger(
                            resultSet.getLong("id"),
                            resultSet.getLong("address_id"),
                            resultSet.getString("pass_name"),
                            resultSet.getString("pass_phone")

                    );

                    passengeres.add(passenger);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passengeres;
    }
    }