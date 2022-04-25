import dao.AddressDao;
import dao.CompanyDao;
import dao.PassengerDao;
import dao.TripDao;
import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import dao.impl.TripDaoImpl;
import model.Trip;
import util.CreatDBFromFile;

import java.time.LocalDate;
import java.time.LocalTime;

public class AMS_app {

    public static void main(String[] args) {
        AddressDao addressDao = new AddressDaoImpl();
        CompanyDao companyDao = new CompanyDaoImpl();
        PassengerDao passengerDao = new PassengerDaoImpl();
        TripDao tripDao = new TripDaoImpl();

        CreatDBFromFile creatDBFromFile = new CreatDBFromFile();

//        creatDBFromFile.creatComp("src/main/java/resource/companies.txt");
//        creatDBFromFile.creatAddress("src/main/java/resource/passengers.txt");
        creatDBFromFile.creatPassenger("src/main/java/resource/passengers.txt");
//        creatDBFromFile.creatTrip("src/main/java/resource/trip1.txt");
//        creatDBFromFile.creatPassInTrip("src/main/java/resource/pass_in_trip1.txt");


    }
}