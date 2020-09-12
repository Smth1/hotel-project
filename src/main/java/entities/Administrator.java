package entities;

public final class Administrator extends Employee {
    private final String telephoneNumber;

    public Administrator(String name, int age, String telephoneNumber) {
        super(name, age);
        this.telephoneNumber = telephoneNumber;
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
