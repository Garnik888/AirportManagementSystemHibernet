package service;

import model.Company;

import java.util.Set;

public interface CompanyService {

    Company getById(long id);

    Set<Company> getAll();

    Set<Company> get(int offset, int perPage, String sort);

    void save(Company company);

    void update(long id, Company company);

    void delete(long companyId);

}
