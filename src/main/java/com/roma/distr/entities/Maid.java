package com.roma.distr.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Maid extends Employee {

    @OneToMany(mappedBy = "maid")
    private List<CleaningReport> cleaningReports;

    public Maid(String name, int age) {
        super(name, age);
    }

    public Maid() {
    }

    @Override
    public String toString() {
        return "Maid{" +
                "name='" + super.getName() + '\'' +
                ", age='" + super.getAge() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maid)) return false;
        Maid maid = (Maid) o;
        return super.getId().equals(maid.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId());
    }
}