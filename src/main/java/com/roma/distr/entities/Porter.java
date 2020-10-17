package com.roma.distr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Porter extends Employee {

    @OneToMany(mappedBy = "porter")
    private List<HotelClientContract> contracts;

    public Porter() {
    }

    public Porter(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Porter{" +
                "name='" + super.getName() + '\'' +
                ", age='" + super.getAge() + '\'' +
                '}';
    }
}
