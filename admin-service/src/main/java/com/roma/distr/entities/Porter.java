package com.roma.distr.entities;

import javax.persistence.Entity;

@Entity
public class Porter extends Employee {

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
