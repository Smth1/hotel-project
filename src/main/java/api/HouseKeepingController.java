package api;

import entities.CleaningReport;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.HouseKeepingService;

import java.util.List;

@RestController
@RequestMapping("/householding")
public class HouseKeepingController {
    @Autowired
    private ModelMapper modelMapper;

    private HouseKeepingService houseKeepingService;

    @Autowired
    public HouseKeepingController(HouseKeepingService service) {
        this.houseKeepingService = service;
    }

    @GetMapping("/reports")
    public List<CleaningReport> reports() {
        return houseKeepingService.getCleaningReports();
    }

    @PutMapping("/clean-rooms")
    public ResponseEntity<Void> clean() {
        this.houseKeepingService.cleanRooms();

        return ResponseEntity.ok().build();
    }
}
