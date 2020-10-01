package com.roma.distr.services;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.Room;

import java.util.List;

public interface RoomService {
    void addRoom(Room room);
    List<Room> getAvailableRooms();
    List<Room> getUncleanRooms();
    void settleClient(HotelClient client, Room room);
    void moveOutClient(HotelClient client);
}
