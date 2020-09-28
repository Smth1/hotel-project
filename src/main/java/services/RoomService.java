package services;

import entities.HotelClient;
import entities.Room;

import java.util.List;

public interface RoomService {
    void addRoom(Room room);
    List<Room> getAvailableRooms();
    List<Room> getUncleanRooms();
    void settleClient(HotelClient client, Room room);
    void moveOutClient(HotelClient client);
}
