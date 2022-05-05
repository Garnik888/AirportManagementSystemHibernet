package service.impl;

import dao.TripDao;
import dao.impl.TripDaoImpl;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import service.TripService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripServiceImpl implements TripService {

    private SessionFactory sessionFactory;
    private TripDao tdi;

    public TripServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        tdi = new TripDaoImpl(sessionFactory);
    }

    @Override
    public Trip getById(long id) {

        return tdi.getTripById(id);
    }

    @Override
    public Set<Trip> getAll() {

        return tdi.getAll();
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public void save(Trip passenger) {

        tdi.createTrip(passenger);
    }

    @Override
    public void update(long id, Trip passenger) {

        tdi.update(id, passenger);
    }

    @Override
    public void delete(long tripId) {

        tdi.deleteById(tripId);
    }

    @Override
    public List<Trip> getTripsFrom(String city) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT tr FROM Trip tr WHERE townFrom = :city")
                .setParameter("city", city);
        List<Trip> trips = new ArrayList<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT tr FROM Trip tr WHERE townTo = :city")
                .setParameter("city", city);
        List<Trip> trips = new ArrayList<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }
}
