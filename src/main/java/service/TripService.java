package service;

import model.Trip;

import java.util.*;

public interface TripService {

    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int offset, int perPage, String sort);

    void save(Trip passenger);

    void update(long id, Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);


}
