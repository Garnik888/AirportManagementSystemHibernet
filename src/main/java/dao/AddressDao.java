package dao;

import model.Address;

import java.util.Set;

public interface AddressDao {

    void createAddress(Address address);

    void update(long id, Address address);

    void deleteById(long id);

    Address getAddressById(long id);

    Set<Address> getAll();
}
