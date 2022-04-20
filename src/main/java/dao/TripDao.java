package dao;

import model.Trip;

import java.util.Set;

public interface TripDao {

    void createTrip(Trip trip);

    void update(int id, Trip trip);

    void deleteById(int id);

    Trip getTripById(int id);

    Set<Trip> getAll();
}
