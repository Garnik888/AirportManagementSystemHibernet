package service;

import model.Trip;

import java.util.*;

public interface TripService {

    Trip getById(int id);

    Set<Trip> getAll();

    Set<Trip> get(int offset, int perPage, String sort);

    void save(Trip passenger);

    void update(int id, Trip passenger);

    void delete(int tripId);

    List<Trip> getTripsFrom(String city);

    List<Trip> getTripsTo(String city);


}
