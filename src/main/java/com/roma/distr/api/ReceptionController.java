package com.roma.distr.api;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.dto.HotelClientDTO;
import com.roma.distr.services.ReceptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reception")
public class ReceptionController {
    private ReceptionService receptionService;

    @Autowired
    public ReceptionController(ReceptionService service) {
        this.receptionService = service;
    }

    @PostMapping("/client")
    public ResponseEntity<Void> serveClient(@RequestBody HotelClientDTO hotelClientDTO) {
        HotelClient client = new HotelClient(hotelClientDTO.getClientName());
        this.receptionService.serveClient(client);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> moveOutClient(@PathVariable(value = "id") String cliendId) {
        receptionService.moveOutClient(cliendId);
        return ResponseEntity.ok().build();
    }
}
