import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import resource.util.CreatDBFromFile;

public class AMS_app {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(Address.class);
//        configuration.addAnnotatedClass(Company.class);
//        configuration.addAnnotatedClass(Passenger.class);
//        configuration.addAnnotatedClass(Trip.class);
//        configuration.addAnnotatedClass(PassInTrip.class);
//
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        CreatDBFromFile creatDBFromFile = new CreatDBFromFile();
        creatDBFromFile.creatComp("src/main/java/resource/companies.txt", sessionFactory);

    }
}