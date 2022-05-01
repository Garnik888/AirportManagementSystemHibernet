package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "phone", unique = true, nullable = false, length = 50)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", foreignKey = @ForeignKey(name = "address_passenger_fk"))
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pass_in_trip",
    joinColumns = {@JoinColumn(name = "id_psg", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "id_trip",referencedColumnName = "id")})
    Set<Trip> trips = new HashSet<>();

    public Passenger() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id && Objects.equals(name, passenger.name) && Objects.equals(phone, passenger.phone)
                && Objects.equals(address, passenger.address) && Objects.equals(trips, passenger.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, trips);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", trips=" + trips +
                '}';
    }
}
