package services;

import entities.CleaningReport;
import entities.Room;

import java.util.List;

public interface HouseKeepingService {
    void cleanRooms();
    void makeCleaningReport(List<Room> rooms);
    List<CleaningReport> getCleaningReports();
}
