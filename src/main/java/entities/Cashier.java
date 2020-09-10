package entities;

import java.util.UUID;

public final class Cashier extends Employer{
    private final Long id;

    public Cashier(String name) {
        this.name = name;
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        System.out.printf("%-30s %15s \n","Cashier with name " + name, " got a job");
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + name + '\'' +
                '}';
    }
}
