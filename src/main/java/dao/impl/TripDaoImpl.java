package dao.impl;

import dao.TripDao;
import model.Trip;
import service.DatabaseConnectionService;

import java.sql.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class TripDaoImpl implements TripDao {
    @Override
    public void createTrip(Trip trip) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Trip (id,comp_id,plane,town_from,town_to,time_out, time_in)" +
                        " VALUES ('" +
                        trip.getId() + "', '" +
                        trip.getIdComp() + "', '" +
                        trip.getPlane() + "', '" +
                        trip.getTownFrom() + "', '" +
                        trip.getTownTo() + "', '" +
                        trip.getTimeOut() + "', '" +
                        trip.getTimeIn() + "');";

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
    public void update(long id, Trip trip) {
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Trip " +
                                         "SET comp_id = ?," +
                                         "plane = ?," +
                                         "town_from = ?," +
                                         "town_to = ?," +
                                         "time_out = ?," +
                                         "time_in = ?" +
                                         "WHERE id = ?"
                         )
            ) {
                preparedStatement.setLong(1, trip.getIdComp());
                preparedStatement.setString(2, trip.getPlane());
                preparedStatement.setString(3, trip.getTownFrom());
                preparedStatement.setString(4, trip.getTownTo());
                preparedStatement.setTime(5, Time.valueOf(trip.getTimeOut()));
                preparedStatement.setTime(6, Time.valueOf(trip.getTimeIn()));
                preparedStatement.setLong(7, id);

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
            String query = "DELETE FROM Trip WHERE id = " + id + ";";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Trip getTripById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Trip trip = null;
        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Trip WHERE id = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                trip = new Trip(
                        resultSet.getLong("id"),
                        resultSet.getLong("comp_id"),
                        resultSet.getString("plane"),
                        resultSet.getString("town_from"),
                        resultSet.getString("town_to"),
                        LocalTime.parse(resultSet.getTime("time_out").toString()),
                        LocalTime.parse(resultSet.getTime("time_in").toString())

                );
            }
        } catch (SQLException e) {
            System.out.println("Wrong query for Trip with id=" + id);
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection cannot close");
            }
        }

        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Set<Trip> tripses = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(
                                 "SELECT * FROM Trip"
                         )
            ) {
                tripses = new HashSet<>();

                Trip trip;
                while (resultSet.next()) {
                    trip = new Trip(
                            resultSet.getInt("id"),
                            resultSet.getInt("comp_id"),
                            resultSet.getString("plane"),
                            resultSet.getString("town_from"),
                            resultSet.getString("town_to"),
                            LocalTime.parse(resultSet.getTime("time_out").toString()),
                            LocalTime.parse(resultSet.getTime("time_in").toString())
                    );

                    tripses.add(trip);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripses;
    }
}
