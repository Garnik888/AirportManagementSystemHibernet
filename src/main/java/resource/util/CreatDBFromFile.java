package resource.util;

import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import model.Address;
import model.Company;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatDBFromFile {

    public void creatComp(String path, SessionFactory sessionFactory) {

        CompanyDaoImpl cdi = new CompanyDaoImpl(sessionFactory);
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

    public void creatAddress(String path, SessionFactory sessionFactory) {

        AddressDaoImpl adi = new AddressDaoImpl(sessionFactory);
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

//    public void creatPassenger(String path) {
//
//        PassengerDaoImpl pdi = new PassengerDaoImpl();
//        Passenger passenger = new Passenger();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//
//            String[] words;
//            String line;
//
//            while (br.ready()) {
//
//                line = br.readLine();
//
//                if (line.contains("'")) {
//                    line = line.replaceAll("'", "՛");
//                }
//                words = line.split(",");
//                passenger.setName(words[0]);
//                passenger.setPhone(words[1]);
//                passenger.setIdAddress(getAddressId(words[2], words[3]));
//
//                pdi.createPassenger(passenger);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void creatTrip(String path) {
//
//        TripDaoImpl tdi = new TripDaoImpl();
//        Trip trip = new Trip();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//
//            String[] words;
//            String line;
//
//            while (br.ready()) {
//                line = br.readLine();
//
//                line = line.replace("��", "");
//                line = line.replaceAll("", "");
//                System.out.println(line);
//                words = line.split(",");
//
//                trip.setId(Long.parseLong(words[0]));
//                trip.setIdComp(Long.parseLong(words[1]));
//                trip.setPlane(words[2]);
//                trip.setTownFrom(words[3]);
//                trip.setTownTo(words[4]);
//                trip.setTimeOut(LocalTime.parse(words[5].split(" ")[1]));
//                trip.setTimeIn(LocalTime.parse(words[6].split(" ")[1]));
//
//                tdi.createTrip(trip);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void creatPassInTrip(String path) {
//
//        PassInTripDaoImpl pdi = new PassInTripDaoImpl();
//        PassInTrip passInTrip = new PassInTrip();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))){
//
//            String [] words;
//            String line;
//
//            while (br.ready()) {
//
//                line = br.readLine();
//                System.out.println(line);
//                words = line.split(",");
//
//                System.out.println(line);
//
//                passInTrip.setIdTrip(Long.parseLong(words[0]));
//                passInTrip.setIdPsg(Long.parseLong(words[1]));
//                passInTrip.setDate(LocalDate.parse(words[2].split(" ")[0]));
//                passInTrip.setPlace(words[3]);
//
//                pdi.createPassInTrip(passInTrip);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Long getAddressId(String country, String city) {
//
//        Connection connection =
//                DatabaseConnectionService.DB_INSTANCE.createConnection();
//
//        PreparedStatement ps=null;
//        ResultSet rs;
//        Long id = 0L;
//
//        try {
//            assert connection != null;
//            ps = connection.prepareStatement("SELECT id FROM address WHERE country = ? AND city = ?");
//
//            ps.setNString(1, country.replace(" ' ",""));
//            ps.setNString(2, city.replace(" ' ",""));
//
//            rs = ps.executeQuery();
//            if (rs.next()) {
//
//                id = rs.getLong("id");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                assert connection != null;
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("Connection cannot close");
//            }
//        }
//
//        return id;
//    }

}
