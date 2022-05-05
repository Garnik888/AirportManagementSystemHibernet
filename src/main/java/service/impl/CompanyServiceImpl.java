package service.impl;

import dao.CompanyDao;
import dao.impl.CompanyDaoImpl;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import service.CompanyService;

import java.util.HashSet;
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

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        HashSet companies;

        Query query = session.createQuery("SELECT c FROM Company c ORDER BY :SORT").
                setParameter("SORT", sort).setMaxResults(perPage).setFirstResult(offset);

        companies = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return companies;
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
