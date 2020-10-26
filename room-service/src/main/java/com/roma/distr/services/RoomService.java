package com.roma.distr.services;

import com.roma.distr.entities.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    void addRoom(Room room);
    List<Room> getAvailableRooms();
    List<Room> getUncleanRooms();
    void setClean(UUID roomId);
}
