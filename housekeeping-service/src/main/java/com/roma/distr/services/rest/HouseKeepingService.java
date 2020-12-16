package com.roma.distr.services.rest;

import com.roma.distr.entities.CleaningReport;

import java.util.List;
import java.util.UUID;

public interface HouseKeepingService {
    void cleanRooms();
    void makeCleaningReport(List<UUID> rooms);
    List<CleaningReport> getCleaningReports();
}
