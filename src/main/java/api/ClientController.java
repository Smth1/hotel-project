package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.HotelClient;
import entities.HotelClientContract;
import entities.dto.HotelClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<HotelClient> getAll() {
        return clientService.getClients();
    }

    @GetMapping("/contracts")
    public List<HotelClientContract> getAllContracts() {
        return clientService.getContracts();
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

    @PostMapping("/clients")
    public ResponseEntity<Void> createClient(@RequestBody HotelClientDTO hotelClientDTO) {
        HotelClient client = new HotelClient(hotelClientDTO.getClientName());
        clientService.addClient(client);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> removeClient(@PathVariable(value="id") String id) {
        List<HotelClient> clients = clientService.getClients();
        for (HotelClient client : clients) {
            if (client.getClientId().toString().equals(id)) {
                clientService.removeClient(client);
            }
        }

        return ResponseEntity.ok().build();
    }
}
