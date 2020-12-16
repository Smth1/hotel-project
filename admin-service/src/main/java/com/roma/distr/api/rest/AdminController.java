package com.roma.distr.api.rest;

import com.roma.distr.entities.Administrator;
import com.roma.distr.entities.Cashier;
import com.roma.distr.entities.Maid;
import com.roma.distr.entities.Porter;
import com.roma.distr.entities.dto.AdministratorDTO;
import com.roma.distr.entities.dto.CashierDTO;
import com.roma.distr.entities.dto.MaidDTO;
import com.roma.distr.entities.dto.PorterDTO;
import com.roma.distr.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.MissingResourceException;

@RestController
@RequestMapping("/administration")
public class AdminController {
    @Autowired
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String controller() {
        return "I am working .....";
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<Administrator> admin() {
        try {
            final Administrator admin = adminService.getAdmin();

            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/maid")
    public ResponseEntity<Maid> randomMaid() {
        try {
            final Maid maid = adminService.getRandomMaid();

            return new ResponseEntity<>(maid, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/cashier")
    public ResponseEntity<Cashier> randomCashier() {
        try {
            final Cashier cashier = adminService.getRandomCashier();

            return new ResponseEntity<>(cashier, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/porter")
    public ResponseEntity<Porter> randomPorter() {
        try {
            final Porter porter = adminService.getRandomPorter();

            return new ResponseEntity<>(porter, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/admin")
    public ResponseEntity<Void> addAdmin(@RequestBody AdministratorDTO admin) {
        Administrator administrator = new Administrator(admin.getName(),
                admin.getAge(), admin.getTelephoneNumber());
        adminService.addEmployee(administrator);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/maid")
    public ResponseEntity<Maid> addMaid(@RequestBody MaidDTO maidDTO) {
        Maid maid = new Maid(maidDTO.getName(), maidDTO.getAge());
        adminService.addEmployee(maid);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/cashier")
    public ResponseEntity<Cashier> addCashier(@RequestBody CashierDTO cashierDTO) {
        Cashier cashier = new Cashier(cashierDTO.getName(), cashierDTO.getAge());
        adminService.addEmployee(cashier);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/porter")
    public ResponseEntity<Porter> addPorter(@RequestBody PorterDTO porterDTO) {
        Porter porter = new Porter(porterDTO.getName(), porterDTO.getAge());
        adminService.addEmployee(porter);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}

