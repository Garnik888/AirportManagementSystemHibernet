package service.impl;

import dao.AllDao;
import dao.PassInTripDao;
import dao.impl.PassInTripDaoImpl;
import dao.impl.PassengerDaoImpl;
import model.PassInTrip;
import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import service.PassengerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {

    private SessionFactory sessionFactory;
    private AllDao<Passenger> pdi;
    private PassInTripDao pitd;

    private Trip trip;

    public PassengerServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        pdi = new PassengerDaoImpl(sessionFactory);
        pitd = new PassInTripDaoImpl(sessionFactory);
    }

    @Override
    public Passenger getById(long id) {

        return pdi.getById(id);
    }

    @Override
    public Set<Passenger> getAll() {

        return pdi.getAll();
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Set<Passenger> passengers;

        Query query = session.createQuery("SELECT p FROM Passenger p ORDER BY :SORT").
                setParameter("SORT", sort).setMaxResults(perPage).setFirstResult(offset);

        passengers = new HashSet<>(query.getResultList());

        session.getTransaction().commit();
        session.close();

        return passengers;
    }

    @Override
    public void save(Passenger passenger) {

        pdi.create(passenger);
    }

    @Override
    public void update(long id, Passenger passenger) {

        pdi.update(id, passenger);
    }

    @Override
    public void delete(long passengerId) {

        pdi.deleteById(passengerId);
    }

    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Passenger> passengers;
        Query query = session.createQuery("SELECT p FROM Passenger p JOIN PassInTrip pit ON p.id=pit.psgId   "
                + " WHERE pit.tripId = :TID").setParameter("TID", tripNumber);

        passengers = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return passengers;

    }

    @Override
    public void registerTrip(PassInTrip passInTrip) {

        pitd.createPassInTrip(passInTrip);
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM PassInTrip WHERE psgId = :PID AND tripId = :TID").
                setParameter("PID", passengerId).setParameter("TID", tripNumber).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
