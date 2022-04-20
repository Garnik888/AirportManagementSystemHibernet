package dao;

import model.Company;

import java.util.Set;

public interface CompanyDao {

    void createCompany(Company company);

    void update(int id, Company company);

    void deleteById(int id);

    Company getCompanyById(int id);

    Set<Company> getAll();
}
