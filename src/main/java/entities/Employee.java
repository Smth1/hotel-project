package entities;

import java.util.UUID;

public abstract class Employee{

    private final Long id;
    private final String name;
    private final int age;

    public Employee(String name, int age){
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", age=" + this.getAge() +
                '}';
    }
}