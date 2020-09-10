package entities;

import java.util.Objects;
import java.util.UUID;

public final class Maid extends Employer {
    private Long id;


    public Maid(String name) {
        this.name = name;
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        System.out.printf("%-30s %15s \n","Maid with name " + name, " got a job");
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Maid{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maid)) return false;
        Maid maid = (Maid) o;
        return id.equals(maid.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
