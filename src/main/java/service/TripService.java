package service;

import model.Trip;

import java.util.*;

public interface TripService {

    Trip getById(long id);

    Set<Trip> getAll();

    Set<Trip> get(int offset, int perPage, String sort);

    Trip save(Trip passenger);

    Trip update(Trip passenger);

    void delete(long tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);


}
