package services;

import entities.Administrator;
import entities.CleaningReport;
import entities.Maid;
import entities.Room;

import java.util.ArrayList;
import java.util.List;

public final class HousekeepingService {
    private final RoomService roomService;
    private final AdminService adminService;
    private final List<CleaningReport> reports;

    public HousekeepingService(RoomService roomService, AdminService adminService) {
        this.roomService = roomService;
        this.adminService = adminService;
        this.reports = new ArrayList<>();

        System.out.println("Created housekeeping Service");
    }

    public void cleanRooms() {
        List<Room> uncleanRooms =  roomService.getUncleanRooms();
        this.makeCleaningReport(uncleanRooms);
        for (Room room : uncleanRooms) {

            room.setClean();

            System.out.println("Cleaning room: " + room.getNumber());
        }

        System.out.println("All rooms are clean");
    }

    public void makeCleaningReport(List<Room> rooms) {
        Administrator admin = adminService.getAdmin();
        Maid maid = adminService.getRandomMaid();
        CleaningReport report =
                new CleaningReport(admin, maid, rooms);
        this.reports.add(report);
    }

    public List<CleaningReport> getCleaningReports() {
        return reports;
    }

    @Override
    public String toString() {
        return "HousekeepingService{" +
                "roomService=" + roomService +
                ", adminService=" + adminService +
                '}';
    }
}
