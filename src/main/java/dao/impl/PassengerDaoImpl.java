package dao.impl;

import dao.PassengerDao;
import model.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Set;

public class PassengerDaoImpl implements PassengerDao {

    public PassengerDaoImpl() {

    }

    @Override
    public void createPassenger(Passenger passenger, SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(passenger);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Passenger passenger) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Passenger getPassengerById(long id) {
        return null;
    }

    @Override
    public Set<Passenger> getAll() {
        return null;
    }
}