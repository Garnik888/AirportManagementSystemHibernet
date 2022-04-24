package model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Trip {

    private long id;
    private long idComp;
    private String plane;
    private String townFrom;
    private String townTo;
    private LocalTime timeOut;
    private LocalTime timeIn;

    public Trip() {

    }

    public Trip(long idComp, String plane,
                String townFrom, String townTo,
                LocalTime timeOut, LocalTime timeIn) {
        this.idComp = idComp;
        this.plane = plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Trip(long id, long idComp, String plane,
                String townFrom, String townTo,
                LocalTime timeOut, LocalTime timeIn) {
        this.id = id;
        this.idComp = idComp;
        this.plane = plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdComp() {
        return idComp;
    }

    public void setIdComp(long idComp) {
        this.idComp = idComp;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }


    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id == trip.id && idComp == trip.idComp && Objects.equals(plane, trip.plane) && Objects.equals(townFrom, trip.townFrom) && Objects.equals(townTo, trip.townTo) && Objects.equals(timeOut, trip.timeOut) && Objects.equals(timeIn, trip.timeIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idComp, plane, townFrom, townTo, timeOut, timeIn);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", idComp=" + idComp +
                ", plane='" + plane + '\'' +
                ", townFrom='" + townFrom + '\'' +
                ", townTo='" + townTo + '\'' +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                "}\n";
    }
}
