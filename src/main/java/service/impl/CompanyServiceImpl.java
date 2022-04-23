package service.impl;

import dao.impl.CompanyDaoImpl;
import model.Company;
import service.CompanyService;

import java.util.Set;


public class CompanyServiceImpl implements CompanyService {
    private CompanyDaoImpl cdi = new CompanyDaoImpl();

    @Override
    public Company getById(int id) {

        return cdi.getCompanyById(id);
    }

    @Override
    public Set<Company> getAll() {

        return cdi.getAll();
    }

    @Override
    public Set<Company> get(int offset, int perPage, String sort) {

        return null;
    }

    @Override
    public void save(Company company) {

        cdi.createCompany(company);
    }

    @Override
    public void update(int compId, Company company) {

        cdi.update(compId,company);
    }

    @Override
    public void delete(int companyId) {

        cdi.deleteById(companyId);
    }
}
