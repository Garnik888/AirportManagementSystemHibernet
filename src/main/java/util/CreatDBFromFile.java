package util;

import dao.PassInTripDao;
import dao.impl.*;
import model.*;
import org.hibernate.SessionFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class CreatDBFromFile {

    private static AddressDaoImpl addressDao = new AddressDaoImpl();

    public static void creatComp(String path, SessionFactory sessionFactory) {

        CompanyDaoImpl cdi = new CompanyDaoImpl();
        String[] words;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {

            while (br.ready()) {

                line = br.readLine();

                if (line.contains("'")) {
                    line = line.replace("'", "՛");
                }

                Company com = new Company();

                words = line.split(",");
                com.setCompanyName(words[0]);
                com.setFoundingDate(LocalDate.parse(words[1], DateTimeFormatter.ofPattern("M/d/yyyy")));

                cdi.createCompany(com, sessionFactory);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Address> createAddress(String path, SessionFactory sessionFactory) {

        Set<Address> add1 = new HashSet<>();
        Address address = null;
        String[] words;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = line.replace("'", "");
                words = line.split(",");

                address = new Address();

                address.setCountry(words[2]);
                address.setCity(words[3]);

                add1.add(address);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return add1;
    }

    public static void creatPassenger(String path, SessionFactory sessionFactory) {

        Set<Address> setAdd = createAddress(path, sessionFactory);

        Passenger passenger = new Passenger();
        PassengerDaoImpl passengerDao = new PassengerDaoImpl();

        String line;
        String[] words;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (true) {
                try {
                    if ((line = bufferedReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (line.contains("'")) {
                    line = line.replace("'", "՛");
                }

                words = line.split(",");

                passenger.setName(words[0]);
                passenger.setPhone(words[1]);

                Address address = new Address();
                address.setCountry(words[2]);
                address.setCity(words[3]);

                for (Address address1 : setAdd) {
                    if (address1.equals(address)) {
                        passenger.setAddress(address1);
                        passengerDao.createPassenger(passenger, sessionFactory);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void creatTrip(String path, SessionFactory sessionFactory) {

        CompanyDaoImpl cdi = new CompanyDaoImpl();
        TripDaoImpl tdi = new TripDaoImpl();
        Trip trip = new Trip();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String[] words;
            String line;

            while (br.ready()) {
                line = br.readLine();

                words = line.split(",");

                trip.setId(Long.parseLong(words[0]));
                Company company = cdi.getCompanyById(Long.parseLong(words[1]), sessionFactory);
                trip.setCompany(company);
                trip.setPlane(words[2]);
                trip.setTownFrom(words[3]);
                trip.setTownTo(words[4]);
                trip.setTimeOut(LocalTime.parse(words[5].split(" ")[1]));
                trip.setTimeIn(LocalTime.parse(words[6].split(" ")[1]));

                tdi.createTrip(trip, sessionFactory);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void creatPassInTrip(String path, SessionFactory sessionFactory) {

        PassInTripDao passInTripDao = new PassInTripDaoImpl();
        PassInTrip passInTrip = new PassInTrip();
        String[] words;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                line = line.replace("'", "");
                words = line.split(",");
                passInTrip.setTripId(Long.parseLong(words[0]));
                passInTrip.setPsgId(Long.parseLong(words[1]));
                passInTrip.setDate(LocalDate.parse(words[2],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                passInTrip.setPlace(words[3]);
                passInTripDao.createPassInTrip(passInTrip, sessionFactory);
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
