package dao;

import model.PassInTrip;

import java.util.Set;

public interface PassInTripDao {

    void createCompany(PassInTrip passInTrip);

    void update(int id, PassInTrip passInTrip);

    void deleteById(int id);

    PassInTrip getCompanyById(int id);

    Set<PassInTrip> getAll();
}
