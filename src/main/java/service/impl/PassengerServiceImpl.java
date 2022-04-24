package service.impl;

import dao.PassengerDao;
import dao.impl.PassInTripDaoImpl;
import dao.impl.PassengerDaoImpl;
import model.PassInTrip;
import model.Passenger;
import model.Trip;
import service.DatabaseConnectionService;
import service.PassengerService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {

    PassengerDaoImpl pdi = new PassengerDaoImpl();
    PassInTripDaoImpl passInTripDaoImpl = new PassInTripDaoImpl();
    @Override
    public Passenger getById(long id) {

        return pdi.getPassengerById(id);
    }

    @Override
    public Set<Passenger> getAll() {

        return pdi.getAll();
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {

        Set<Passenger> passengerSet = null;

        try (Connection connection =
                     DatabaseConnectionService.DB_INSTANCE.createConnection()) {

            assert connection!=null;
            try(Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM passenger " +
                        "ORDER BY" + sort + "LIMIT" + perPage +
                        "OFFSET" + offset + ";")
                    ) {

                passengerSet = new HashSet<>();
                Passenger passenger;

                while (resultSet.next()){

                    passenger = new Passenger(
                            resultSet.getLong("id"),
                            resultSet.getLong("address_id"),
                            resultSet.getString("pass_name"),
                            resultSet.getString("pass_phone")
                    );

                    passengerSet.add(passenger);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return passengerSet;
    }

    @Override
    public void save(Passenger passenger) {

        pdi.createPassenger(passenger);
    }

    @Override
    public void update(long passId, Passenger passenger) {

        pdi.update(passId, passenger);
    }

    @Override
    public void delete(long passengerId) {

        pdi.deleteById(passengerId);
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        List<Passenger> passengeres = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet =
                         statement.executeQuery(
                                 "SELECT * FROM Passenger p JOIN pass_in_trip pit ON " +
                                         "p.id = pit.psg_id " +
                                         "WHERE trip_id = tripNumber"
                         )
            ) {
                passengeres = new ArrayList<>();

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

    @Override
    public void registerTrip(PassInTrip passInTrip) {

        passInTripDaoImpl.createPassInTrip(passInTrip);
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

        Statement statement = null;
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        try {
            assert connection != null;
            statement = connection.createStatement();
            String query = "DELETE" + " FROM pass_in_trip " +
                    " WHERE psg_id = " + passengerId + " AND trip_id= " + tripNumber +
                    "";
            statement.execute(query);
            statement.close();
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
}
