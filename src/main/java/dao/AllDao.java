package dao;

import model.Address;
import org.hibernate.SessionFactory;

import java.util.Set;

public interface AllDao <T> {

    void create(T t);

    void update(long id, T t);

    void deleteById(long id);

    T getById(long id);

    Set<T> getAll();
}
