package dao.impl;

import dao.AddressDao;
import model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.HashSet;
import java.util.Set;

public class AddressDaoImpl implements AddressDao {

    private SessionFactory sessionFactory;

    public AddressDaoImpl() {

    }

    public AddressDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createAddress(Address address) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(address);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Address address) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Address old  = getAddressById(id);
        address.setId(old.getId());
        session.merge(address);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Address address = session.find(Address.class, id);
        session.delete(address);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Address getAddressById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Address address = session.find(Address.class, id);

        session.getTransaction().commit();
        session.close();
        return address;
    }

    @Override
    public Set<Address> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Address> query = session.createQuery("SELECT add FROM Address add", Address.class);
        Set<Address> addresses = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return addresses;
    }
}
