package util;

import dao.impl.*;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreatDBFromFile {

    public static void creatComp(String path,SessionFactory sessionFactory) {

        CompanyDaoImpl cdi = new CompanyDaoImpl();
        Company com = new Company();
        String[] words;
        String line;
        String[] comData;

        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {

            while (br.ready()) {

                line = br.readLine();

                if (line.contains("'")) {
                    line = line.replace("'", "՛");
                }
                words = line.split(",");
                com.setCompanyName(words[0]);
                com.setFoundingDate(LocalDate.parse(words[1], DateTimeFormatter.ofPattern("M/d/yyyy")));

                cdi.createCompany(com);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void creatPassenger(String path, SessionFactory sessionFactory) {

        PassengerDaoImpl pdi = new PassengerDaoImpl();
        Passenger passenger = new Passenger();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String[] words;
            String line;

            while (br.ready()) {

                line = br.readLine();

                if (line.contains("'")) {
                    line = line.replaceAll("'", "՛");
                }

                Address address = new Address();
                words = line.split(",");
                passenger.setName(words[0]);
                passenger.setPhone(words[1]);
                address.setCountry(words[2]);
                address.setCity(words[3]);
                passenger.setAddress(address);

                pdi.createPassenger(passenger, sessionFactory);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        PassInTripDaoImpl pdi = new PassInTripDaoImpl();
        PassInTrip passInTrip = new PassInTrip();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String [] words;
            String line;

            while (br.ready()) {

                line = br.readLine();
                System.out.println(line);
                words = line.split(",");

                System.out.println(line);

                PassInTrip.PassInTripId passInTripId = new PassInTrip.PassInTripId(
                        Long.parseLong(words[1]),
                        Long.parseLong(words[0]),
                        LocalDate.parse(words[2].split(" ")[0]));

                passInTrip.setPassInTripId(passInTripId);
                passInTrip.setPlace(words[3]);

                pdi.createPassInTrip(passInTrip, sessionFactory);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
