package dao.impl;

import dao.CompanyDao;
import model.Address;
import model.Company;
import service.DatabaseConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {
    @Override
    public void createCompany(Company company) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Company (company_name, founding_date)" +
                        " VALUES ('" +
                        company.getCompanyName() + "', '" +
                        company.getFounding_date().toString() + "');";

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
    public void update(long id, Company company) {
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(
                                 "UPDATE Company " +
                                         "SET company_name = ?," +
                                         "founding_date = ? " +
                                         "WHERE id = ?"
                         )
            ) {
                preparedStatement.setString(1, company.getCompanyName());
                preparedStatement.setDate(2, Date.valueOf(company.getFounding_date()));
                preparedStatement.setLong(3, id);

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
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Company WHERE id = " + id + ";";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company getCompanyById(long id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Company company = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Company WHERE id = ?"
            );

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("company_name"),
                        LocalDate.parse(resultSet.getDate("founding_date").toString())
                );
            }

        } catch (SQLException e) {
            System.out.println("Wrong query for Company with id=" + id);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection cannot close");
            }
        }

        return company;
    }

    @Override
    public Set<Company> getAll() {
        Set<Company> companies = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(
                             "SELECT * FROM Company"
                     )
        ) {
            companies = new HashSet<>();

            Company company;
            while (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("company_name"),
                        LocalDate.parse(resultSet.getDate("founding_date").toString())
                );

                companies.add(company);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return companies;
    }
    }

