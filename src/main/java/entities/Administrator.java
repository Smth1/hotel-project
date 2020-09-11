package entities;

public final class Administrator extends Employee {
    private final String telephoneNumber;

    public Administrator(String name, int age, String telephoneNumber) {
        super(name, age);
        this.telephoneNumber = telephoneNumber;

        System.out.printf("%-30s %15s \n","Administrator with name " + name, " got a job");
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "name='" + super.getName() + '\'' +
                "telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
