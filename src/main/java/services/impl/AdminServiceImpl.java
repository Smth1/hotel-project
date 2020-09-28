package services.impl;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdministratorRepository;
import repository.EmployeeRepository;
import services.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Administrator getAdmin() {
        return administratorRepository.findAll().get(0);
    }

    @Override
    public Maid getRandomMaid() {
        List<Maid> maids = new ArrayList<>();
        List<Employee> allEmployees = (List<Employee>)employeeRepository.findAll();

        Random random = new Random();

        for (Employee employee : allEmployees) {
            if (employee instanceof Maid) {
                maids.add((Maid)employee);
            }
        }

        return maids.get(random.nextInt(maids.size()));
    }

    @Override
    public Cashier getRandomCashier() {
        List<Cashier> cashiers = new ArrayList<>();
        List<Employee> allEmployees = (List<Employee>)employeeRepository.findAll();
        Random random = new Random();

        for (Employee employee : allEmployees) {
            if (employee instanceof Cashier) {
                cashiers.add((Cashier)employee);
            }
        }

        return cashiers.get(random.nextInt(cashiers.size()));
    }

    @Override
    public Porter getRandomPorter() {
        List<Porter> porters = new ArrayList<>();
        List<Employee> allEmployees = (List<Employee>)employeeRepository.findAll();
        Random random = new Random();

        for (Employee employee : allEmployees) {
            if (employee instanceof Porter) {
                porters.add((Porter) employee);
            }
        }

        return porters.get(random.nextInt(porters.size()));
    }

    @Override
    public String toString() {
        return "AdminService{" +
                "admin=" + administratorRepository.findAll().get(0) +
                ", employers=" + employeeRepository.findAll() +
                '}';
    }
}
