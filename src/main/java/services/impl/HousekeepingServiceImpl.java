package services.impl;

import entities.Administrator;
import entities.CleaningReport;
import entities.Maid;
import entities.Room;

import repository.CleaningReportRepository;
import services.HouseKeepingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HousekeepingServiceImpl implements HouseKeepingService {
    @Autowired
    private RoomServiceImpl roomService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private CleaningReportRepository cleaningReportRepository;


    @Override
    public void cleanRooms() {
        List<Room> uncleanRooms =  roomService.getUncleanRooms();
        this.makeCleaningReport(uncleanRooms);
        for (Room room : uncleanRooms) {

            room.setClean();

            System.out.println("Cleaning room: " + room.getNumber());
        }

        System.out.println("All rooms are clean");
    }

    @Transactional
    @Override
    public void makeCleaningReport(List<Room> rooms) {
        Administrator admin = adminService.getAdmin();
        Maid maid = adminService.getRandomMaid();
        CleaningReport report =
                new CleaningReport(admin, maid, rooms);
        cleaningReportRepository.save(report);
    }

    @Transactional
    @Override
    public List<CleaningReport> getCleaningReports() {
        return cleaningReportRepository.findAll();
    }

    @Override
    public String toString() {
        return "HousekeepingService{" +
                "roomService=" + roomService +
                ", adminService=" + adminService +
                '}';
    }
}
