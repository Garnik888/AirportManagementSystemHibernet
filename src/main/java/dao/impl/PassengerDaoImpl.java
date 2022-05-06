package dao.impl;

import dao.AllDao;
import model.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class PassengerDaoImpl implements AllDao<Passenger> {

    private SessionFactory sessionFactory;

    public PassengerDaoImpl() {

    }

    public PassengerDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Passenger passenger) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(passenger);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Passenger passenger) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Passenger pass = getById(id);
        passenger.setId(pass.getId());
        session.merge(passenger);

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Passenger WHERE id = :ID").
                setParameter("ID", id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Passenger getById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Passenger passenger = session.find(Passenger.class, id);

        session.getTransaction().commit();
        session.close();

        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Passenger> query = session.createQuery("SELECT pass FROM Passenger pass", Passenger.class);
        Set<Passenger> passengers = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return passengers;
    }
}