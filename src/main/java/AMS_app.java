import dao.AddressDao;
import dao.CompanyDao;
import dao.PassengerDao;
import dao.TripDao;
import dao.impl.AddressDaoImpl;
import dao.impl.CompanyDaoImpl;
import dao.impl.PassengerDaoImpl;
import dao.impl.TripDaoImpl;
import org.hibernate.SessionFactory;
import util.CreatDBFromFile;
import util.HibernateSessionFactoryUtil;

public class AMS_app {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

        CreatDBFromFile creatDBFromFile = new CreatDBFromFile(sessionFactory);

        creatDBFromFile.creatComp("src/main/java/resource/companies.txt");
        creatDBFromFile.creatPassenger("src/main/java/resource/passengers .txt");
        creatDBFromFile.creatTrip("src/main/java/resource/trip.txt");
        creatDBFromFile.creatPassInTrip("src/main/java/resource/pass_in_trip.txt");
    }
}