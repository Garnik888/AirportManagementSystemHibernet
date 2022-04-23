package service.impl;

import dao.TripDao;
import dao.impl.TripDaoImpl;
import model.Trip;
import service.TripService;

import java.util.List;
import java.util.Set;

public class TripServiceImpl implements TripService {

    private TripDaoImpl tdi = new TripDaoImpl();

    @Override
    public Trip getById(int id) {
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
    public void update(int tripId, Trip passenger) {

        tdi.update(tripId, passenger);
    }

    @Override
    public void delete(int tripId) {

        tdi.deleteById(tripId);
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        return null;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        return null;
    }
}
