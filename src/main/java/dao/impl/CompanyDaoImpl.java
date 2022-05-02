package dao.impl;

import dao.CompanyDao;
import model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateSessionFactoryUtil;


import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CompanyDaoImpl implements CompanyDao {

    public CompanyDaoImpl () {

    }

    @Override
    public void createCompany(Company company) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
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
    public Company getCompanyById(long id, SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Company company = null;
        try {

            company= session.get(Company.class, id);

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {

            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return company;
    }

    @Override
    public Set<Company> getAll() {
        return null;
    }
}

