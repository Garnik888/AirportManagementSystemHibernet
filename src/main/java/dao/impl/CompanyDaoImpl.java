package dao.impl;

import dao.CompanyDao;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {

    private SessionFactory sessionFactory;

    public CompanyDaoImpl () {

    }

    public CompanyDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }
    @Override
    public void createCompany(Company company) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(company);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Company company) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Company getCompanyById(long id) {
        return null;
    }

    @Override
    public Set<Company> getAll() {
        return null;
    }
}

