package model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pass_in_trip")
public class PassInTrip implements Serializable {

    @Column(name = "place", nullable = false, length = 50)
    private String place;

    @EmbeddedId
    private PassInTripId passInTripId;

    public PassInTrip() {

    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PassInTripId getPassInTripId() {
        return passInTripId;
    }

    public void setPassInTripId(PassInTripId passInTripId) {
        this.passInTripId = passInTripId;
    }

    @Embeddable
    public static class PassInTripId implements Serializable {

        @Column(name = "id_trip")
        public Long psgId;
        @Column(name = "psg_id")
        public Long tripId;
        private LocalDate date;

        public PassInTripId() {

        }

        public PassInTripId(Long psgId, Long tripId, LocalDate date) {
            this.psgId = psgId;
            this.tripId = tripId;
            this.date = date;
        }

        public Long getPsgId() {
            return psgId;
        }

        public void setPsgId(Long psgId) {
            this.psgId = psgId;
        }

        public Long getTripId() {
            return tripId;
        }

        public void setTripId(Long tripId) {
            this.tripId = tripId;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "PassInTripId{" +
                    "psgId=" + psgId +
                    ", tripId=" + tripId +
                    ", date=" + date +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTrip that = (PassInTrip) o;
        return Objects.equals(place, that.place) && Objects.equals(passInTripId, that.passInTripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, passInTripId);
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                "place='" + place + '\'' +
                ", passInTripId=" + passInTripId +
                '}';
    }
}
