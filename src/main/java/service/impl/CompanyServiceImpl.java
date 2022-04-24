package service.impl;

import dao.impl.CompanyDaoImpl;
import model.Company;
import service.CompanyService;
import service.DatabaseConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class CompanyServiceImpl implements CompanyService {
    private CompanyDaoImpl cdi = new CompanyDaoImpl();

    @Override
    public Company getById(long id) {

        return cdi.getCompanyById(id);
    }

    @Override
    public Set<Company> getAll() {

        return cdi.getAll();
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {

        Set<Company> companySet = null;

        try(Connection connection =
                    DatabaseConnectionService.DB_INSTANCE.createConnection()
        ) {
            assert connection != null;
            try(Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM passenger " +
                        "ORDER BY" + sort + "LIMIT" + perPage +
                        "OFFSET" + offset + ";")
            ) {

                companySet = new HashSet<>();
                Company company;
                while (resultSet.next()) {

                    company = new Company(
                    resultSet.getLong("id"),
                    resultSet.getString("company_name"),
                    LocalDate.parse(resultSet.getDate("founding_date").toString()));

                    companySet.add(company);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return companySet;
    }

    @Override
    public void save(Company company) {

        cdi.createCompany(company);
    }

    @Override
    public void update(long compId, Company company) {

        cdi.update(compId,company);
    }

    @Override
    public void delete(long companyId) {

        cdi.deleteById(companyId);
    }
}
