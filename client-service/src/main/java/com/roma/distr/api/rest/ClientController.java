package com.roma.distr.api.rest;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.dto.ClientsDTO;
import com.roma.distr.entities.dto.HotelClientDTO;
import com.roma.distr.services.rest.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<ClientsDTO> getAll() {
        List<HotelClient> clients = clientService.getClients();
        ClientsDTO clientsDTO = new ClientsDTO();
        clientsDTO.setClients(clients.stream()
                .map( (el) -> new HotelClientDTO(el.getClientId(),el.getName()))
                .collect(Collectors.toList()));

        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Void> serveClient(@RequestBody HotelClientDTO hotelClientDTO) {
        HotelClient client = new HotelClient(hotelClientDTO.getClientName());
        this.clientService.serveClient(client);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> moveOutClient(@PathVariable(value = "id") String clientId) {
        this.clientService.moveOutClient(clientId);
        return ResponseEntity.ok().build();
    }
}
