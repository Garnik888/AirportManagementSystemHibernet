package dao;

import model.Trip;
import org.hibernate.SessionFactory;

import java.util.Set;

public interface TripDao {

    void createTrip(Trip trip);

    void update(long id, Trip trip);

    void deleteById(long id);

    Trip getTripById(long id);

    Set<Trip> getAll();
}
