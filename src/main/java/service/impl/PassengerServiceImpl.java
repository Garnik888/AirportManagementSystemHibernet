package service.impl;

import dao.PassengerDao;
import dao.impl.PassengerDaoImpl;
import model.PassInTrip;
import model.Passenger;
import org.hibernate.SessionFactory;
import service.PassengerService;

import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {

    private SessionFactory sessionFactory;
    private PassengerDao pdi;

    public PassengerServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        pdi = new PassengerDaoImpl(sessionFactory);
    }

    @Override
    public Passenger getById(long id) {

        return pdi.getPassengerById(id);
    }

    @Override
    public Set<Passenger> getAll() {

        return pdi.getAll();
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public void save(Passenger passenger) {

        pdi.createPassenger(passenger);
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
        return null;
    }

    @Override
    public void registerTrip(PassInTrip passInTrip) {

    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {

    }
}
