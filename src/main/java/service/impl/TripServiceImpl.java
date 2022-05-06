package service.impl;

import dao.AllDao;
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
    private AllDao<Trip> tdi;

    public TripServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        tdi = new TripDaoImpl(sessionFactory);
    }

    @Override
    public Trip getById(long id) {

        return tdi.getById(id);
    }

    @Override
    public Set<Trip> getAll() {

        return tdi.getAll();
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Set<Trip> trips;
        Query query = session.createQuery("SELECT t FROM Trip t ORDER BY :SORT").
                setParameter("SORT", sort).setMaxResults(perPage).setFirstResult(offset);

        trips = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }

    @Override
    public void save(Trip passenger) {

        tdi.create(passenger);
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

        Query query = session.createQuery("SELECT tr FROM Trip tr WHERE townFrom = :CITY")
                .setParameter("CITY", city);
        List<Trip> trips = new ArrayList<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT tr FROM Trip tr WHERE townTo = :CITY")
                .setParameter("CITY", city);
        List<Trip> trips = new ArrayList<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return trips;
    }
}
