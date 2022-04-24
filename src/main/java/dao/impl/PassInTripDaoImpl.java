package dao.impl;

import dao.PassInTripDao;
import model.PassInTrip;
import service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PassInTripDaoImpl implements PassInTripDao {
    @Override
    public void createPassInTrip(PassInTrip passInTrip) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query = "INSERT INTO pass_in_trip (trip_id, psg_id, date, place)" +
                "VALUES ('" +
                passInTrip.getIdTrip() + "', '" +
                passInTrip.getIdPsg() + "', '" +
                passInTrip.getDate() + "', '" +
                passInTrip.getPlace() + "');";

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
}
