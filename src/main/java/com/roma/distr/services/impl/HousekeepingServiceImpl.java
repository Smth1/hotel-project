package com.roma.distr.services.impl;

import com.roma.distr.entities.Administrator;
import com.roma.distr.entities.CleaningReport;
import com.roma.distr.entities.Maid;
import com.roma.distr.entities.Room;

import com.roma.distr.repository.CleaningReportRepository;
import com.roma.distr.services.HouseKeepingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (uncleanRooms == null || uncleanRooms.size() == 0) {
            System.out.println("All rooms are clean");
            return;
        }
        this.makeCleaningReport(uncleanRooms);
        for (Room room : uncleanRooms) {

            room.setClean();
            roomService.addRoom(room);

            System.out.println("Cleaning room: " + room.getNumber());
        }
    }

    @Override
    public void makeCleaningReport(List<Room> rooms) {
        Administrator admin = adminService.getAdmin();
        Maid maid = adminService.getRandomMaid();
        CleaningReport report =
                new CleaningReport(admin, maid, rooms);
        cleaningReportRepository.save(report);
    }


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
