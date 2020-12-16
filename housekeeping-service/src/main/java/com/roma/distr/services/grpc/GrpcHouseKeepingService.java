package com.roma.distr.services.grpc;

import com.roma.distr.entities.CleaningReport;

import java.util.List;
import java.util.UUID;

public interface GrpcHouseKeepingService {
    void cleanRooms();
    void makeCleaningReport(List<UUID> rooms);
    List<CleaningReport> getCleaningReports();
}
