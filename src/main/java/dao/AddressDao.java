package dao;

import model.Address;

import java.util.Set;

public interface AddressDao {

    void createAddress(Address address);

    void update(int id, Address address);

    void deleteById(int id);

    Address getAddressById(int id);

    Set<Address> getAll();
}
