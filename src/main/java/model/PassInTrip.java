package model;

import java.time.LocalDate;
import java.util.Objects;

public class PassInTrip {

    private long idTrip;
    private long idPsg;
    private LocalDate date;
    private String place;

    public PassInTrip() {

    }

    public PassInTrip(long idTrip, long idPsg, LocalDate date, String place) {
        this.idTrip = idTrip;
        this.idPsg = idPsg;
        this.date = date;
        this.place = place;
    }

    public long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(long idTrip) {
        this.idTrip = idTrip;
    }

    public long getIdPsg() {
        return idPsg;
    }

    public void setIdPsg(long idPsg) {
        this.idPsg = idPsg;
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
        return idTrip == that.idTrip && idPsg == that.idPsg && Objects.equals(date, that.date) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrip, idPsg, date, place);
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                "idTrip=" + idTrip +
                ", idPsg=" + idPsg +
                ", date=" + date +
                ", place='" + place + '\'' +
                "}\n";
    }
}
