package com.roma.distr.api.rest;

import com.roma.distr.dto.ContractsDTO;
import com.roma.distr.dto.HotelClientContractDTO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ContractController {
    //private static final String URL = "http://contract-service:8083";
    private static final String URL = "http://localhost:8083";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @GetMapping("/contracts")
    public ResponseEntity<ContractsDTO> getAllContracts() {
        ResponseEntity<ContractsDTO> responseEntity = restTemplate
                .exchange(URL + "/contracts", HttpMethod.GET, headersEntity, ContractsDTO.class);

        return responseEntity;
    }

    @GetMapping("/contract")
    public ResponseEntity<HotelClientContractDTO> getContractOfClient(
            @RequestParam(value = "clientId") String clientId) {
        ResponseEntity<HotelClientContractDTO> responseEntity = restTemplate
                .exchange(URL + "/contract?clientId=" + clientId, HttpMethod.GET, headersEntity, HotelClientContractDTO.class);

        return responseEntity;
    }
}
