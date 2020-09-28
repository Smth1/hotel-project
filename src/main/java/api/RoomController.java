package api;

import entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.RoomService;

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
}
