package service;

import model.Passenger;
import model.Trip;

import java.util.*;

public interface PassengerService {

    Passenger getById(int id);

    Set<Passenger> getAll();

    Set<Passenger> get(int offset, int perPage, String sort);

    void save(Passenger passenger);

    void update(int id, Passenger passenger);

    void delete(int passengerId);

    List<Passenger> getPassengersOfTrip(int tripNumber);

    void registerTrip(Trip trip, Passenger passenger);

    void cancelTrip(int passengerId, int tripNumber);
}
