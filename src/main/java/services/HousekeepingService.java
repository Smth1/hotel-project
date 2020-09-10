package services;

import entities.Room;

import java.util.List;

public final class HousekeepingService {
    private final RoomService roomService;
    private final AdminService adminService;

    public HousekeepingService(RoomService roomService, AdminService adminService) {
        this.roomService = roomService;
        this.adminService = adminService;

        System.out.println("Created Housekeeping Service");
    }

    public void cleanRooms() {
        List<Room> uncleanRooms =  roomService.getUncleanRooms();
        for (Room room : uncleanRooms) {
            room.setClean();

            System.out.println("Cleaning room: " + room);
        }

        System.out.println("All rooms are clean");
    }
}
