package com.roma.distr.api;

import com.roma.distr.entities.Room;
import com.roma.distr.entities.dto.RoomDTO;
import com.roma.distr.services.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService service) {
        this.roomService = service;
    }

    @GetMapping("/rooms")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    @PostMapping("/room")
    public ResponseEntity<Void> addAdmin(@RequestBody RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getNumber(), roomDTO.getClientNumber());
        roomService.addRoom(room);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
