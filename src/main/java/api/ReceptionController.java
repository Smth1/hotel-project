package api;

import entities.HotelClient;
import entities.dto.HotelClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ReceptionService;

@RestController
public class ReceptionController {
    private ReceptionService receptionService;

    @Autowired
    public ReceptionController(ReceptionService service) {
        this.receptionService = service;
    }

    @PostMapping("/clients")
    public ResponseEntity<Void> serveClient(@RequestBody HotelClientDTO hotelClientDTO) {
        HotelClient client = new HotelClient(hotelClientDTO.getClientName());
        this.receptionService.serveClient(client);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> moveOutClient(@PathVariable(value = "id") String cliendId) {
        return ResponseEntity.ok().build();
    }
}
