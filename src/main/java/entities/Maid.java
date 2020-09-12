package entities;

import java.util.Objects;

public final class Maid extends Employee {

    public Maid(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Maid{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
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
