package service;

import model.PassInTrip;
import model.Passenger;
import model.Trip;

import java.util.*;

public interface PassengerService {

    Passenger getById(long id);

    Set<Passenger> getAll();

    Set<Passenger> get(int offset, int perPage, String sort);

    void save(Passenger passenger);

    void update(long id, Passenger passenger);

    void delete(long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(PassInTrip passInTrip);

    void cancelTrip(long passengerId, long tripNumber);
}
