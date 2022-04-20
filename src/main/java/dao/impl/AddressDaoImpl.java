package dao.impl;

import dao.AddressDao;
import model.Address;
import service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    @Override
    public void createAddress(Address address) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Employee (country, city)" +
                        " VALUES ('" +
                        address.getCountry() + "', '" +
                        address.getCity() + ");";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, Address address) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Address getAddressById(int id) {
        return null;
    }

    @Override
    public Set<Address> getAll() {
        return null;
    }
}
