package services;

import entities.HotelClient;
import entities.Room;

import java.util.ArrayList;
import java.util.List;

public final class RoomService {
    private final List<Room> rooms;

    public RoomService() {
        rooms = new ArrayList<>();

        System.out.println("Created room service");
    }

    public void addRoom(Room room) {
        rooms.add(room);

        System.out.println("Added room: " + room);
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            if (room.isFree()) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public List<Room> getUncleanRooms() {
        List<Room> uncleanRooms = new ArrayList<>();

        for(Room room : rooms) {
            if (!room.isClean()) {
                uncleanRooms.add(room);
            }
        }

        return uncleanRooms;
    }

    public void settleClient(HotelClient client, Room room) {
        room.addClient(client);
    }

    public void moveOutClient(HotelClient client) {
        for (Room room : rooms) {
            room.removeClient(client);
        }
    }

    @Override
    public String toString() {
        return "Hotel rooms{" +
                "rooms=" + rooms +
                '}';
    }
}
