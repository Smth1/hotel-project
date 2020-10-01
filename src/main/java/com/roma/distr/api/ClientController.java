package com.roma.distr.api;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.entities.dto.ClientsDTO;
import com.roma.distr.entities.dto.ContractsDTO;
import com.roma.distr.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ClientsDTO clientsDTO = new ClientsDTO(clients);

        return new ResponseEntity<>(clientsDTO, HttpStatus.OK);
    }

    @GetMapping("/contracts")
    public ResponseEntity<ContractsDTO> getAllContracts() {
        List<HotelClientContract> contractList = clientService.getContracts();
        ContractsDTO contractsDTO = new ContractsDTO(contractList);

        return new ResponseEntity<>(contractsDTO, HttpStatus.OK);
    }

    @GetMapping("/contract")
    public ResponseEntity<HotelClientContract> getContractOfClient(
            @RequestParam(value = "client") String clientName) {
        List<HotelClient> clients = this.clientService.getClients();
        HotelClientContract contract = null;

        for (HotelClient client : clients) {
            if(client.getName().equals(clientName)) {
                contract = this.clientService.getContractOfClient(client);
            }
        }
        if (contract != null)
            return new ResponseEntity<>(contract, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
