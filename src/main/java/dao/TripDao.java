package dao;

import model.Trip;

import java.util.Set;

public interface TripDao {

    void createCompany(Trip trip);

    void update(int id, Trip trip);

    void deleteById(int id);

    Trip getCompanyById(int id);

    Set<Trip> getAll();
}
