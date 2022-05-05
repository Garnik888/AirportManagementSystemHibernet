package util;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Passenger.class);
            configuration.addAnnotatedClass(Trip.class);
            configuration.addAnnotatedClass(PassInTrip.class);

            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }
}
