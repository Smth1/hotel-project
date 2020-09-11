package entities;

public final class Cashier extends Employee {

    public Cashier(String name, int age) {
        super(name, age);

        System.out.printf("%-30s %15s \n","Cashier with name " + name, " got a job");
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
