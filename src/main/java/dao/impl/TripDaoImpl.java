package dao.impl;

import dao.TripDao;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Set;

public class TripDaoImpl implements TripDao {

    private SessionFactory sessionFactory;

    public TripDaoImpl() {

    }

    public TripDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createTrip(Trip trip, SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(trip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Trip trip) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Trip getTripById(long id) {
        return null;
    }

    @Override
    public Set<Trip> getAll() {
        return null;
    }
}
