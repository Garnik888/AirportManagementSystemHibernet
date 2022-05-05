package dao;

import model.Address;
import model.PassInTrip;
import org.hibernate.SessionFactory;

public interface PassInTripDao {

    void createPassInTrip(PassInTrip passInTrip);
}
