package service;

import model.Company;

import java.util.Set;

public interface CompanyService {

    Company getById(int id);

    Set<Company> getAll();

    Set<Company> get(int offset, int perPage, String sort);

    void save(Company company);

    void update(int id, Company company);

    void delete(int companyId);

}
