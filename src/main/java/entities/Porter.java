package entities;

public final class Porter extends Employee {

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
