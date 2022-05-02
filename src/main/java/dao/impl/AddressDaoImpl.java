package dao.impl;

import dao.AddressDao;
import model.Address;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    public AddressDaoImpl() {

    }

    @Override
    public void createAddress(Address address, SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(address);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Address address) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Address getAddressById(long id) {

        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Address.class, id);
    }

    @Override
    public Set<Address> getAll() {
        return null;
    }
}
