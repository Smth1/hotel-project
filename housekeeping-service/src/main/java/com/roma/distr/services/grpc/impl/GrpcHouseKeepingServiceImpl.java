package com.roma.distr.services.grpc.impl;

import com.roma.distr.api.grpc.*;
import com.roma.distr.entities.CleaningReport;
import com.roma.distr.repository.CleaningReportRepository;
import com.roma.distr.services.grpc.GrpcHouseKeepingService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GrpcHouseKeepingServiceImpl implements GrpcHouseKeepingService {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final ManagedChannel roomManagedChannedl;
    private final AdminServiceGrpc.AdminServiceBlockingStub adminServiceBlockingStub;
    private final MaidServiceGrpc.MaidServiceBlockingStub maidServiceBlockingStub;
    private final RoomServiceGrpc.RoomServiceBlockingStub roomServiceBlockingStub;

    private CleaningReportRepository cleaningReportRepository;

    @Autowired
    public GrpcHouseKeepingServiceImpl(CleaningReportRepository cleaningReportRepository) {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.roomManagedChannedl = ManagedChannelBuilder.forAddress(URL, 6565)
                .usePlaintext().build();
        this.adminServiceBlockingStub = AdminServiceGrpc.newBlockingStub(administrationManagedChannel);
        this.maidServiceBlockingStub = MaidServiceGrpc.newBlockingStub(administrationManagedChannel);
        this.roomServiceBlockingStub = RoomServiceGrpc.newBlockingStub(roomManagedChannedl);

        this.cleaningReportRepository = cleaningReportRepository;
    }

    @Override
    public void cleanRooms() {
        List<UUID> uncleanRooms = this.getUncleanRooms();
        if (uncleanRooms.size() == 0) {
            System.out.println("All rooms are clean");
            return;
        }

        this.makeCleaningReport(uncleanRooms);
        for (UUID room : uncleanRooms) {

            System.out.println(room);
            this.setClean(room.toString());

            System.out.println("Cleaning room: " + room);
        }
    }

    @Override
    public void makeCleaningReport(List<UUID> rooms) {
        AdministratorResponseGet get_administrator = this.adminServiceBlockingStub.getAdministrator(AdministratorRequestGet.newBuilder()
                .setAdministratorRequest("Get administrator")
                .build());

        MaidResponseGet get_maid = this.maidServiceBlockingStub.getMaid(MaidRequestGet.newBuilder()
                .setMaidRequest("Get maid")
                .build());

        AdministratorTransfer administratorTransfer = get_administrator.getResult();
        MaidTransfer maidTransfer = get_maid.getResult();

        UUID adminId = UUID.fromString(administratorTransfer.getId());
        UUID maidId = UUID.fromString(maidTransfer.getId());
        StringBuilder stringBuilder = new StringBuilder();
        for (UUID roomId : rooms) {
            stringBuilder.append(roomId);
            stringBuilder.append(",");
        }
        CleaningReport report =
                new CleaningReport(adminId, maidId, stringBuilder.toString());
        cleaningReportRepository.save(report);
    }

    @Override
    public List<CleaningReport> getCleaningReports() {
        return cleaningReportRepository.findAll();
    }

    private List<UUID> getUncleanRooms() {
        RoomsResponseGet get_unclean_rooms = this.roomServiceBlockingStub.getUncleanRooms(RoomsRequestGet.newBuilder()
                .setRequest("Get unclean rooms")
                .build());
        List<UUID> rooms = get_unclean_rooms.getRoomsTransferList().stream().map((el) ->
                UUID.fromString(el.getId())).collect(Collectors.toList());

        return rooms;
    }

    private void setClean(String roomId) {
        this.roomServiceBlockingStub.makeClean(MakeCleanRequest.newBuilder()
                .setRoomId(roomId)
                .build());
    }
}
