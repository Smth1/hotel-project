package services;

import entities.*;

public interface AdminService {
    void addEmployee(Employee employer);
    Administrator getAdmin();
    Maid getRandomMaid();
    Cashier getRandomCashier();
    Porter getRandomPorter();
}
