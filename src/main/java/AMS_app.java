import dao.AddressDao;
import dao.CompanyDao;
import dao.PassengerDao;
import dao.TripDao;
import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import dao.impl.TripDaoImpl;
import model.Address;
import model.Company;
import model.Passenger;
import model.Trip;

import java.time.LocalDate;
import java.time.LocalTime;

public class AMS_app {

    public static void main(String[] args) {
        AddressDao addressDao = new AddressDaoImpl();
        CompanyDao companyDao = new CompanyDaoImpl();
        PassengerDao passengerDao = new PassengerDaoImpl();
        TripDao tripDao = new TripDaoImpl();


//        for (int i = 4; i < 8; i++) {
//            tripDao.createTrip(
//                    new Trip(i, 2,
//                            "ARM" + i,
//                            "Yerevan" + i,
//                            "LOndon" + i, LocalTime.of(14, 25, 36), LocalTime.of(20, 12, 03)
//                    )
//            );
//        }

        tripDao.update(4, new Trip(2,3,"bugj","dsfd","dgdf", LocalTime.of(1, 1, 1),LocalTime.of(2, 2, 2)));
        System.out.println(tripDao.getTripById(4));

    }
}