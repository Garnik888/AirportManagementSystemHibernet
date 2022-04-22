package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
public class Company {

    private long id;
    private String companyName;
    private LocalDate founding_date;

    public Company(String companyName, LocalDate founding_date) {
        this.companyName = companyName;
        this.founding_date = founding_date;
    }

    public Company(long id, String companyName, LocalDate founding_date) {
        this.id = id;
        this.companyName = companyName;
        this.founding_date = founding_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDate getFounding_date() {
        return founding_date;
    }

    public void setFounding_date(LocalDate founding_date) {
        this.founding_date = founding_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(companyName, company.companyName) && Objects.equals(founding_date, company.founding_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, founding_date);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", founding_date=" + founding_date +
                "}\n";
    }
}
