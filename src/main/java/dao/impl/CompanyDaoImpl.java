package dao.impl;


import dao.AllDao;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements AllDao<Company> {

    private SessionFactory sessionFactory;

    public CompanyDaoImpl() {

    }

    public CompanyDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Company company) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(company);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Company company) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Company old  = getById(id);
        company.setId(old.getId());

        session.merge(company);

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteById(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

       session.createQuery("DELETE FROM Company WHERE id = :ID")
               .setParameter("ID",id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Company getById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.find(Company.class, id);

        session.getTransaction().commit();
        session.close();

        return company;
    }

    @Override
    public Set<Company> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Company> query = session.createQuery("SELECT com FROM Company com", Company.class);

        Set<Company> companies = new HashSet<>(query.getResultList());

        return companies;
    }
}

