package model;

import java.util.Objects;

public class Passenger {

    private long id;
    private long idAddress;
    private String name;
    private String phone;

    public Passenger() {

    }
    public Passenger(long idAddress, String name, String phone) {
        this.idAddress = idAddress;
        this.name = name;
        this.phone = phone;
    }

    public Passenger(long id, long idAddress, String name, String phone) {
        this.id = id;
        this.idAddress = idAddress;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(long idAddress) {
        this.idAddress = idAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id && idAddress == passenger.idAddress && Objects.equals(name, passenger.name) && Objects.equals(phone, passenger.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idAddress, name, phone);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", idAddress=" + idAddress +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                "}\n";
    }
}
