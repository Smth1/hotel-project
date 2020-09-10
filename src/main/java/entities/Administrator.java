package entities;

import java.util.UUID;

public final class Administrator extends Employer {
    private final Long id;

    public Administrator(String name) {
        this.name = name;
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        System.out.printf("%-30s %15s \n","Administrator with name " + name, " got a job");
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + name + '\'' +
                '}';
    }
}
