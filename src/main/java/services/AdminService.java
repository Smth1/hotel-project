package services;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class AdminService {
    private final Administrator admin;
    private final List<Employee> employees;

    public AdminService(Administrator admin) {
        this.admin = admin;
        employees = new ArrayList<>();
        System.out.println("Created AdminService with administrator: " + admin);
    }

    public void addEmployer(Employee employer) {
        employees.add(employer);
        System.out.println("Added employer: " + employer);
    }

    public Administrator getAdmin() {
        return admin;
    }

    public Maid getRandomMaid() {
        List<Maid> maids = new ArrayList<>();
        Random random = new Random();

        for (Employee employee : employees) {
            if (employee instanceof Maid) {
                maids.add((Maid)employee);
            }
        }

        return maids.get(random.nextInt(maids.size()));
    }

    public Cashier getRandomCashier() {
        List<Cashier> cashiers = new ArrayList<>();
        Random random = new Random();

        for (Employee employee : employees) {
            if (employee instanceof Cashier) {
                cashiers.add((Cashier)employee);
            }
        }

        return cashiers.get(random.nextInt(cashiers.size()));
    }

    public Porter getRandomPorter() {
        List<Porter> porters = new ArrayList<>();
        Random random = new Random();

        for (Employee employee : employees) {
            if (employee instanceof Porter) {
                porters.add((Porter) employee);
            }
        }

        return porters.get(random.nextInt(porters.size()));
    }

    @Override
    public String toString() {
        return "AdminService{" +
                "admin=" + admin +
                ", employers=" + employees +
                '}';
    }
}
