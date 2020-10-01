package com.roma.distr.services;

import com.roma.distr.entities.*;

public interface AdminService {
    void addEmployee(Employee employer);
    Administrator getAdmin();
    Maid getRandomMaid();
    Cashier getRandomCashier();
    Porter getRandomPorter();
}
