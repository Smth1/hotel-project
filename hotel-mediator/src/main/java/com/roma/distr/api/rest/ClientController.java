package com.roma.distr.api.rest;

import com.roma.distr.dto.ClientsDTO;
import com.roma.distr.dto.HotelClientDTO;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {
    //private static final String URL = "http://client-service:8082";
    private static final String URL = "http://localhost:8082";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @GetMapping("/clients")
    public ResponseEntity<ClientsDTO> getAll() {
        ResponseEntity<ClientsDTO> responseEntity = restTemplate
                .exchange(URL + "/clients", HttpMethod.GET, headersEntity, ClientsDTO.class);

        return responseEntity;
    }

    @PostMapping("/client")
    public ResponseEntity<Void> serveClient(@RequestBody HotelClientDTO hotelClientDTO) {
        HttpEntity<HotelClientDTO> deliverClient = new HttpEntity<>(hotelClientDTO, headers);

        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/client", HttpMethod.POST, deliverClient, Void.class);

        return responseEntity;
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> moveOutClient(@PathVariable(value = "id") String clientId) {
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/clients/" + clientId,
                        HttpMethod.DELETE, null, Void.class);

        return responseEntity;
    }
}
