package api;

import entities.Administrator;
import entities.Cashier;
import entities.Maid;
import entities.Porter;
import entities.dto.AdministratorDTO;
import entities.dto.CashierDTO;
import entities.dto.MaidDTO;
import entities.dto.PorterDTO;
import org.springframework.web.bind.annotation.*;
import services.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.MissingResourceException;

@RestController
@CrossOrigin
@RequestMapping("administration")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<Administrator> admin() {
        return new ResponseEntity<>(adminService.getAdmin(), HttpStatus.OK);
    }

//    @GetMapping
//    public String admin() {
//        return "new ResponseEntity<>(adminService.getAdmin(), HttpStatus.OK)";
//    }

    @PostMapping
    public ResponseEntity<Void> createAdministration(@RequestBody AdministratorDTO admin) {
        System.out.println();
        System.out.println(admin);
        Administrator administrator = new Administrator(admin.getName(),
                admin.getAge(), admin.getTelephoneNumber());
        adminService.addEmployee(administrator);

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/maid")
    public ResponseEntity<Maid> randomMaid() {
        try {
            final Maid maid = adminService.getRandomMaid();

            return new ResponseEntity<>(maid, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cashier")
    public ResponseEntity<Cashier> randomCashier() {
        try {
            final Cashier cashier = adminService.getRandomCashier();

            return new ResponseEntity<>(cashier, HttpStatus.CREATED);
        } catch (MissingResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/porter")
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
