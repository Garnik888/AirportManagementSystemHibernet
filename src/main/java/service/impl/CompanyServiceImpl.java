package service.impl;

import dao.impl.CompanyDaoImpl;
import model.Company;
import service.CompanyService;

import java.util.Set;


public class CompanyServiceImpl implements CompanyService {
    CompanyDaoImpl cdi=new CompanyDaoImpl();
    @Override
    public Company getById(long id) { return null;
    }

    @Override
    public Set<Company> getAll() {
        return null;
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public Company save(Company company) {
        return null;
    }

    @Override
    public Company update(Company company) {
        return null;
    }

    @Override
    public void delete(long companyId) {

    }
}
