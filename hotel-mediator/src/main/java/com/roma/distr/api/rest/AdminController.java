package com.roma.distr.api.rest;

import com.roma.distr.dto.AdministratorDTO;
import com.roma.distr.dto.CashierDTO;
import com.roma.distr.dto.MaidDTO;
import com.roma.distr.dto.PorterDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/administration")
public class AdminController {
    //private static final String URL = "http://admin-service:8081";
    private static final String URL = "http://localhost:8081";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @GetMapping(value = "/admin")
    public ResponseEntity<AdministratorDTO> admin() {
        ResponseEntity<AdministratorDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.GET, headersEntity, AdministratorDTO.class);

        return responseEntity;
    }

    @GetMapping(value = "/maid")
    public ResponseEntity<MaidDTO> randomMaid() {
        ResponseEntity<MaidDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/maid", HttpMethod.GET, headersEntity, MaidDTO.class);

        return responseEntity;
    }

    @GetMapping(value = "/cashier")
    public ResponseEntity<CashierDTO> randomCashier() {
        ResponseEntity<CashierDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/cashier", HttpMethod.GET, headersEntity, CashierDTO.class);

        return responseEntity;
    }

    @GetMapping(value = "/porter")
    public ResponseEntity<PorterDTO> randomPorter() {
        ResponseEntity<PorterDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/porter", HttpMethod.GET, headersEntity, PorterDTO.class);

        return responseEntity;
    }

// ----------------------------------------------------------------------
    @PostMapping("/admin")
    public ResponseEntity<Void> addAdmin(@RequestBody AdministratorDTO admin) {
        HttpEntity<AdministratorDTO> deliverObject = new HttpEntity<>(admin, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.POST, deliverObject, Void.class);

        return new ResponseEntity<>(responseEntity.getStatusCode());
    }

    @PostMapping("/maid")
    public ResponseEntity<Void> addMaid(@RequestBody MaidDTO maidDTO) {
        HttpEntity<MaidDTO> deliverObjectMaid = new HttpEntity<>(maidDTO, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/maid", HttpMethod.POST, deliverObjectMaid, Void.class);

        return responseEntity;
    }

    @PostMapping("/cashier")
    public ResponseEntity<Void> addCashier(@RequestBody CashierDTO cashierDTO) {
        HttpEntity<CashierDTO> deliverObjectCashier = new HttpEntity<>(cashierDTO, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/cashier", HttpMethod.POST, deliverObjectCashier, Void.class);

        return responseEntity;
    }

    @PostMapping("/porter")
    public ResponseEntity<Void> addPorter(@RequestBody PorterDTO porterDTO) {
        HttpEntity<PorterDTO> deliverObjectPorter = new HttpEntity<>(porterDTO, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/porter", HttpMethod.POST, deliverObjectPorter, Void.class);

        return responseEntity;
    }

}
