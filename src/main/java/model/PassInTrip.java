package model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pass_in_trip")
public class PassInTrip {

    @Id()
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "place", nullable = false)
    private String place;

    public PassInTrip() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTrip that = (PassInTrip) o;
        return Objects.equals(date, that.date) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, place);
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                ", date=" + date +
                ", place='" + place + '\'' +
                "}\n";
    }
}
