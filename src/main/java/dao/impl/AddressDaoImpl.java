package dao.impl;

import dao.AddressDao;
import model.Address;
import service.DatabaseConnectionService;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    @Override
    public void createAddress(Address address) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Address (country, city)" +
                        " VALUES ('" +
                        address.getCountry() + "', '" +
                        address.getCity() + "');";

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
    public void update(long id, Address address) {
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Address " +
                                         "SET country = ?," +
                                         "city = ? " +
                                         "WHERE id = ?"
                         )
            ) {
                preparedStatement.setString(1, address.getCountry());
                preparedStatement.setString(2, address.getCity());
                preparedStatement.setLong(3, id);

                preparedStatement.executeUpdate();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Address WHERE id = " + id + ";";
            statement.execute(query);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Address getAddressById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Address address = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Address WHERE id = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("country"),
                        resultSet.getString("city")
                );
            }

        } catch (SQLException throwables) {
            System.out.println("Wrong query for Address with id=" + id);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }

        return address;
    }

    @Override
    public Set<Address> getAll() {

        Set<Address> addreses = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(
                             "SELECT * FROM Address"
                     )
        ) {
            addreses = new HashSet<>();

            Address address;
            while (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("country"),
                        resultSet.getString("city")
                );

                addreses.add(address);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return addreses;
    }
}
