package dao;

import model.PassInTrip;

import java.util.Set;

public interface PassInTripDao {

    void createPassInTrip(PassInTrip passInTrip);

    void update(int id, PassInTrip passInTrip);

    void deleteById(int id);

    PassInTrip getPassInTripById(int id);

    Set<PassInTrip> getAll();
}
