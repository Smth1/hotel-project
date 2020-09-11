package entities;

public final class Porter extends Employee {

    public Porter(String name, int age) {
        super(name, age);

        System.out.printf("%-30s %15s \n","Porter with name " + name, " got a job");
    }

    @Override
    public String toString() {
        return "Porter{" +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
