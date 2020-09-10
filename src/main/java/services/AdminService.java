package services;

import entities.Administrator;
import entities.Employer;

import java.util.ArrayList;
import java.util.List;

public final class AdminService {
    private final Administrator admin;
    private final List<Employer> employers;

    public AdminService(Administrator admin) {
        this.admin = admin;
        employers = new ArrayList<>();

        System.out.println("Created AdminService with administrator: " + admin);
    }

    public void addEmployer(Employer employer) {
        employers.add(employer);

        System.out.println("Added employer: " + employer);
    }
}
