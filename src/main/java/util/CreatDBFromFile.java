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

    private SessionFactory sessionFactory;

    public CreatDBFromFile(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void creatComp(String path) {

        CompanyDaoImpl cdi = new CompanyDaoImpl(sessionFactory);
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

                cdi.create(com);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Address> createAddress(String path) {

        Set<Address> addressSet = new HashSet<>();
        Address address;
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

                addressSet.add(address);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return addressSet;
    }

    public void creatPassenger(String path) {

        Set<Address> setAdd = createAddress(path);

        Passenger passenger = new Passenger();
        PassengerDaoImpl passengerDao = new PassengerDaoImpl(sessionFactory);

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
                        passengerDao.create(passenger);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatTrip(String path) {

        CompanyDaoImpl cdi = new CompanyDaoImpl(sessionFactory);
        TripDaoImpl tdi = new TripDaoImpl(sessionFactory);
        Trip trip = new Trip();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String[] words;
            String line;

            while (br.ready()) {
                line = br.readLine();

                words = line.split(",");

                trip.setId(Long.parseLong(words[0]));
                Company company = cdi.getById(Long.parseLong(words[1]));
                trip.setCompany(company);
                trip.setPlane(words[2]);
                trip.setTownFrom(words[3]);
                trip.setTownTo(words[4]);
                trip.setTimeOut(LocalTime.parse(words[5].split(" ")[1]));
                trip.setTimeIn(LocalTime.parse(words[6].split(" ")[1]));

                tdi.create(trip);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatPassInTrip(String path) {

        PassInTripDao passInTripDao = new PassInTripDaoImpl(sessionFactory);
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
                passInTripDao.createPassInTrip(passInTrip);
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
