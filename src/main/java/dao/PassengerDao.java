package dao;

import model.Passenger;

import java.util.Set;

public interface PassengerDao {

    void createPassenger(Passenger passenger);

    void update(int id, Passenger passenger);

    void deleteById(int id);

    Passenger getPassengerById(int id);

    Set<Passenger> getAll();
}
