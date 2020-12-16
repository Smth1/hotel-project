package com.roma.distr.api.rest;

import com.roma.distr.entities.Room;
import com.roma.distr.entities.dto.RoomDTO;
import com.roma.distr.entities.dto.RoomsDTO;
import com.roma.distr.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/room-service")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService service) {
        this.roomService = service;
    }

    @GetMapping("/rooms")
    public ResponseEntity<RoomsDTO> getAvailableRooms() {
        List<Room> rooms = roomService.getAvailableRooms();
        RoomsDTO roomsDTO = new RoomsDTO();
        roomsDTO.setRooms(rooms);

        return new ResponseEntity<>(roomsDTO, HttpStatus.OK);
    }

    @GetMapping("/unclean-rooms")
    public List<Room> getUncleanRooms() {
        return roomService.getUncleanRooms();
    }


    @PostMapping("/room")
    public ResponseEntity<Void> addRoom(@RequestBody RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getNumber(), roomDTO.getClientNumber());
        roomService.addRoom(room);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/make-clean/{id}")
    public ResponseEntity<Void> makeClean(@PathVariable(value = "id") String roomId) {
        roomService.setClean(UUID.fromString(roomId));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
