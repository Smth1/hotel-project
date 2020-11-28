package com.roma.distr.api.rest;

import com.roma.distr.dto.CleaningReportsDTO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/housekeeping")
public class HouseKeepingController {
    //private static final String URL = "http://housekeeping-service:8084";
    private static final String URL = "http://localhost:8084";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @GetMapping("/reports")
    public ResponseEntity<CleaningReportsDTO> reports() {
        ResponseEntity<CleaningReportsDTO> responseEntity = restTemplate
                .exchange(URL + "/housekeeping/reports", HttpMethod.GET, headersEntity, CleaningReportsDTO.class);

        return responseEntity;
    }

    @PutMapping("/clean-rooms")
    public ResponseEntity<Void> clean() {
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/housekeeping/clean-rooms", HttpMethod.PUT, null, Void.class);

        return ResponseEntity.ok().build();
    }
}
