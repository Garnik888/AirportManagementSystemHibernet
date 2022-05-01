package service.impl;

import model.Trip;
import service.TripService;

import java.util.List;
import java.util.Set;

public class TripServiceImpl implements TripService {


    @Override
    public Trip getById(long id) {
        return null;
    }

    @Override
    public Set<Trip> getAll() {
        return null;
    }

    @Override
    public Set<Trip> get(int offset, int perPage, String sort) {
        return null;
    }

    @Override
    public void save(Trip passenger) {

    }

    @Override
    public void update(long id, Trip passenger) {

    }

    @Override
    public void delete(long tripId) {

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
