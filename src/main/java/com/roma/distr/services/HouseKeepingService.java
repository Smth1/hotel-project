package com.roma.distr.services;

import com.roma.distr.entities.CleaningReport;
import com.roma.distr.entities.Room;

import java.util.List;

public interface HouseKeepingService {
    void cleanRooms();
    void makeCleaningReport(List<Room> rooms);
    List<CleaningReport> getCleaningReports();
}
