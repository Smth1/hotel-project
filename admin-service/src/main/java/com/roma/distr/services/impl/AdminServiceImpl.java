package com.roma.distr.services.impl;

import com.roma.distr.entities.*;
import com.roma.distr.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roma.distr.services.AdminService;

import java.util.List;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private MaidRepository maidRepository;

    @Autowired
    private PorterRepository porterRepository;

    @Autowired
    private CashierRepository cashierRepository;

    @Override
    public void addEmployee(Employee employee) {
        if (employee instanceof Administrator)
            administratorRepository.save((Administrator)employee);
        else if (employee instanceof Maid)
            maidRepository.save((Maid)employee);
        else if (employee instanceof Porter)
            porterRepository.save((Porter)employee);
        else if (employee instanceof Cashier)
            cashierRepository.save((Cashier)employee);

    }

    @Override
    public Administrator getAdmin() {
        List<Administrator> administratorList = administratorRepository.findAll();
        System.out.println(administratorList);
        return administratorList.get(0);
    }

    @Override
    public Maid getRandomMaid() {
        List<Maid> maids = maidRepository.findAll();
        Random random = new Random();
        int index = random.nextInt(maids.size());
        return maids.get(index);
    }

    @Override
    public Cashier getRandomCashier() {
        List<Cashier> cashiers = cashierRepository.findAll();
        Random random = new Random();

        return cashiers.get(random.nextInt(cashiers.size()));
    }

    @Override
    public Porter getRandomPorter() {
        List<Porter> porters = porterRepository.findAll();
        Random random = new Random();

        return porters.get(random.nextInt(porters.size()));
    }

    @Override
    public String toString() {
        return "AdminService{" +
                "admin=" + administratorRepository.findAll().get(0) +
                '}';
    }
}
