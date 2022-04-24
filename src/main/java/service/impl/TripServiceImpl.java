package service.impl;

import dao.TripDao;
import dao.impl.TripDaoImpl;
import model.Trip;
import service.DatabaseConnectionService;
import service.TripService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripServiceImpl implements TripService {

    private TripDaoImpl tdi = new TripDaoImpl();

    @Override
    public Trip getById(long id) {

        return tdi.getTripById(id);
    }

    @Override
    public Set<Trip> getAll() {

        return tdi.getAll();
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {

        Set<Trip> tripSet = null;

        try (Connection connection =
                     DatabaseConnectionService.DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();

                 ResultSet resultSet = statement.executeQuery("SELECT * FROM passenger " +
                         "ORDER BY" + sort + "LIMIT" + perPage +
                         "OFFSET" + offset + ";")
            ) {
                tripSet = new HashSet<>();
                Trip trip;

                while (resultSet.next()) {

                    trip = new Trip(
                            resultSet.getLong("id"),
                            resultSet.getLong("comp_id"),
                            resultSet.getString("plane"),
                            resultSet.getString("town_from"),
                            resultSet.getString("town_to"),
                            LocalTime.parse(resultSet.getTime("time_out").toString()),
                            LocalTime.parse(resultSet.getTime("time_in").toString())
                    );

                    tripSet.add(trip);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tripSet;
    }

    @Override
    public void save(Trip passenger) {

        tdi.createTrip(passenger);
    }

    @Override
    public void update(long tripId, Trip passenger) {

        tdi.update(tripId, passenger);
    }

    @Override
    public void delete(long tripId) {

        tdi.deleteById(tripId);
    }

    @Override
    public List<Trip> getTripsFrom(String city) {

        List<Trip> tripses = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(
                                 "SELECT * FROM Trip WHERE town_from = city"
                         )
            ) {
                tripses = new ArrayList<>();

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

    @Override
    public List<Trip> getTripsTo(String city) {
        List<Trip> tripses = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(
                                 "SELECT * FROM Trip WHERE town_to = city"
                         )
            ) {
                tripses = new ArrayList<>();

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
