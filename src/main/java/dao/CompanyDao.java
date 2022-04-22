package dao;

import model.Company;

import java.util.Set;

public interface CompanyDao {

    void createCompany(Company company);

    void update(long id, Company company);

    void deleteById(long id);

    Company getCompanyById(long id);

    Set<Company> getAll();
}
