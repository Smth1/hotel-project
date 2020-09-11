package services;

import entities.Administrator;
import entities.Employee;

import java.util.ArrayList;
import java.util.List;

public final class AdminService {
    private final Administrator admin;
    private final List<Employee> employers;

    public AdminService(Administrator admin) {
        this.admin = admin;
        employers = new ArrayList<>();

        System.out.println("Created AdminService with administrator: " + admin);
    }

    public void addEmployer(Employee employer) {
        employers.add(employer);

        System.out.println("Added employer: " + employer);
    }

    @Override
    public String toString() {
        return "AdminService{" +
                "admin=" + admin +
                ", employers=" + employers +
                '}';
    }
}
