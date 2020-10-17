package com.roma.distr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cashier extends Employee {

    @OneToMany(mappedBy = "cashier")
    private List<HotelClientContract> contracts;

    public Cashier() {
    }

    public Cashier(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + super.getName() + '\'' +
                ", age='" + super.getAge() + '\'' +
                '}';
    }
}
