package service;

import model.Company;

import java.util.Set;

public interface CompanyService {

    Company getById(long id);

    Set<Company> getAll();

    Set<Company> get(int offset, int perPage, String sort);

    Company save(Company company);

    Company update(Company company);

    void delete(long companyId);

}
