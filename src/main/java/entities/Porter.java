package entities;

public final class Porter extends Employer {

    public Porter(String name) {
        this.name = name;

        System.out.printf("%-30s %15s \n","Porter with name " + name, " got a job");
    }

    @Override
    public String toString() {
        return "Porter{" +
                "name='" + name + '\'' +
                '}';
    }
}
