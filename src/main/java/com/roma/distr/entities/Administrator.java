package com.roma.distr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Administrator extends Employee {
    private String telephoneNumber;

    @OneToMany(mappedBy = "administrator")
    private List<CleaningReport> cleaningReports;

    @OneToMany(mappedBy = "hotelAdmin")
    private List<HotelClientContract> contracts;

    public Administrator() {

    }

    public Administrator(String name, int age, String telephoneNumber) {
        super(name, age);
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + super.getName() + '\'' +
                "telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
