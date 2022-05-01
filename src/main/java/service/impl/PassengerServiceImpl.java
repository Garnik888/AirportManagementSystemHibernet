package service.impl;

import model.PassInTrip;
import model.Passenger;
import service.PassengerService;

import java.util.List;
import java.util.Set;

public class PassengerServiceImpl implements PassengerService {


    @Override
    public Passenger getById(long id) {
        return null;
    }

    @Override
    public Set<Passenger> getAll() {
        return null;
    }

    @Override
    public Set<Passenger> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public void save(Passenger passenger) {

    }

    @Override
    public void update(long id, Passenger passenger) {

    }

    @Override
    public void delete(long passengerId) {

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
