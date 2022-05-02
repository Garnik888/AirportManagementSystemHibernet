package dao;

import model.Passenger;
import org.hibernate.SessionFactory;

import java.util.Set;

public interface PassengerDao {

    void createPassenger(Passenger passenger, SessionFactory sessionFactory);

    void update(long id, Passenger passenger);

    void deleteById(long id);

    Passenger getPassengerById(long id);

    Set<Passenger> getAll();
}
