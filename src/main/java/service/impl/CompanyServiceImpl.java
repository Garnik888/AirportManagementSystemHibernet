package service.impl;

import dao.CompanyDao;
import dao.impl.CompanyDaoImpl;
import model.Company;
import org.hibernate.SessionFactory;
import service.CompanyService;

import java.util.Set;


public class CompanyServiceImpl implements CompanyService {

    private SessionFactory sessionFactory;
    private CompanyDao cdi;

    public CompanyServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        cdi = new CompanyDaoImpl(sessionFactory);
    }

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
        return null;
    }

    @Override
    public void save(Company company) {

        cdi.createCompany(company);
    }

    @Override
    public void update(long id, Company company) {

        cdi.update(id, company);
    }

    @Override
    public void delete(long companyId) {

        cdi.deleteById(companyId);
    }
}
