package service.impl;

import dao.PassengerDao;
import dao.impl.PassengerDaoImpl;
import model.Passenger;
import model.Trip;
import service.PassengerService;

import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {

    PassengerDaoImpl pdi = new PassengerDaoImpl();

    @Override
    public Passenger getById(int id) {

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
    public void update(int passId, Passenger passenger) {
        pdi.update(passId, passenger);
    }

    @Override
    public void delete(int passengerId) {

    }

    @Override
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        return null;
    }

    @Override
    public void registerTrip(Trip trip, Passenger passenger) {

    }

    @Override
    public void cancelTrip(int passengerId, int tripNumber) {

    }
}
