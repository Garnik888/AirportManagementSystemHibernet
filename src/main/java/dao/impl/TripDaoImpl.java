package dao.impl;

import dao.TripDao;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class TripDaoImpl implements TripDao {

    private SessionFactory sessionFactory;

    public TripDaoImpl() {

    }

    public TripDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createTrip(Trip trip) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(trip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, Trip trip) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Trip tr = getTripById(id);
        trip.setId(tr.getId());
        session.merge(trip);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Trip WHERE id = :id").
                setParameter("id", id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Trip getTripById(long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Trip trip = session.find(Trip.class, id);

        session.getTransaction().commit();
        session.close();

        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<Trip> query = session.createQuery("SELECT tr FROM Trip tr");
        Set<Trip> trips = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }
}
