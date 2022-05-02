package dao.impl;

import dao.PassInTripDao;
import model.PassInTrip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PassInTripDaoImpl implements PassInTripDao {

    public PassInTripDaoImpl() {

    }

    @Override
    public void createPassInTrip(PassInTrip passInTrip, SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(passInTrip);
        session.getTransaction().commit();
        session.close();
    }
}
