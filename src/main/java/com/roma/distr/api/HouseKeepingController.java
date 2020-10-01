package com.roma.distr.api;

import com.roma.distr.entities.CleaningReport;
import com.roma.distr.entities.dto.CleaningReportDTO;
import com.roma.distr.services.HouseKeepingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/housekeeping")
public class HouseKeepingController {
    private HouseKeepingService houseKeepingService;

    @Autowired
    public HouseKeepingController(HouseKeepingService service) {
        this.houseKeepingService = service;
    }

    @GetMapping("/reports")
    public ResponseEntity<CleaningReportDTO> reports() {
        List<CleaningReport> reportsList = houseKeepingService.getCleaningReports();
        CleaningReportDTO reportsDTO = new CleaningReportDTO(reportsList);

        return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
    }

    @PutMapping("/clean-rooms")
    public ResponseEntity<Void> clean() {
        this.houseKeepingService.cleanRooms();

        return ResponseEntity.ok().build();
    }
}
