package util;

import dao.impl.*;
import model.*;
import service.DatabaseConnectionService;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreatDBFromFile {

    public void creatComp(String path) {

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
                comData = words[1].split("/");
                if (comData[0].length() != 2) {
                    if (comData[1].length() != 2) {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-0" +
                                comData[0] + "-0" + comData[1]));
                    } else {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-0" +
                                comData[0] + "-" + comData[1]));
                    }

                } else {

                    if (comData[1].length() != 2) {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-" +
                                comData[0] + "-0" + comData[1]));
                    } else {

                        com.setFounding_date(LocalDate.parse(comData[2] + "-" +
                                comData[0] + "-" + comData[1]));
                    }
                }

                cdi.createCompany(com);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatAddress(String path) {

        AddressDaoImpl adi = new AddressDaoImpl();
        Address address = new Address();
        String[] words;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))
        ) {

            while (br.ready()) {

                line = br.readLine();

                if (line.contains("'")) {
                    line = line.replace("'", "՛");
                }

                words = line.split(",");
                address.setCountry(words[2]);
                address.setCity(words[3]);

                adi.createAddress(address);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatPassenger(String path) {

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
                words = line.split(",");
                passenger.setName(words[0]);
                passenger.setPhone(words[1]);
                passenger.setIdAddress(getAddressId(words[2], words[3]));

                pdi.createPassenger(passenger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatTrip(String path) {

        TripDaoImpl tdi = new TripDaoImpl();
        Trip trip = new Trip();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String[] words;
            String line;

            while (br.ready()) {
                line = br.readLine();

                line = line.replace("��", "");
                line = line.replaceAll("", "");
                System.out.println(line);
                words = line.split(",");

                trip.setId(Long.parseLong(words[0]));
                trip.setIdComp(Long.parseLong(words[1]));
                trip.setPlane(words[2]);
                trip.setTownFrom(words[3]);
                trip.setTownTo(words[4]);
                trip.setTimeOut(LocalTime.parse(words[5].split(" ")[1]));
                trip.setTimeIn(LocalTime.parse(words[6].split(" ")[1]));

                tdi.createTrip(trip);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatPassInTrip(String path) {

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

                passInTrip.setIdTrip(Long.parseLong(words[0]));
                passInTrip.setIdPsg(Long.parseLong(words[1]));
                passInTrip.setDate(LocalDate.parse(words[2].split(" ")[0]));
                passInTrip.setPlace(words[3]);

                pdi.createPassInTrip(passInTrip);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getAddressId(String country, String city) {

        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement ps=null;
        ResultSet rs;
        Long id = 0L;

        try {
            assert connection != null;
            ps = connection.prepareStatement("SELECT id FROM address WHERE country = ? AND city = ?");

            ps.setNString(1, country.replace(" ' ",""));
            ps.setNString(2, city.replace(" ' ",""));

            rs = ps.executeQuery();
            if (rs.next()) {

                id = rs.getLong("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection cannot close");
            }
        }

        return id;
    }
}
