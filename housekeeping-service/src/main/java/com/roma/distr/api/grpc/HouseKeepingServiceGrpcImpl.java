package com.roma.distr.api.grpc;

import com.roma.distr.entities.CleaningReport;
import com.roma.distr.services.grpc.GrpcHouseKeepingService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class HouseKeepingServiceGrpcImpl extends HouseKeepingServiceGrpc.HouseKeepingServiceImplBase {
    private final GrpcHouseKeepingService grpcHouseKeepingService;

    @Autowired
    public HouseKeepingServiceGrpcImpl(GrpcHouseKeepingService grpcHouseKeepingService) {
        this.grpcHouseKeepingService = grpcHouseKeepingService;
    }

    @Override
    public void getAllReports(ReportsRequestGet request, StreamObserver<ReportsResponseGet> responseObserver) {
        String reportsRequest = request.getRequest();
        System.out.println("reportsRequest = " + reportsRequest);

        List<CleaningReport> cleaningReports = grpcHouseKeepingService.getCleaningReports();

        List<CleaningReportTransfer> contractTransfers = cleaningReports.stream()
                .map((el) -> CleaningReportTransfer.newBuilder()
                .setId(el.getId().toString())
                .setAdminId(el.getAdministratorId().toString())
                .setMaidId(el.getMaidId().toString())
                .setRooms(el.getRooms())
                .setCreationDate(el.getCreationDate().toString())
                .build()).collect(Collectors.toList());

        ReportsResponseGet responseGet = ReportsResponseGet.newBuilder()
                .addAllCleaningReportTransfer(contractTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void clean(CleanRequest request, StreamObserver<CleanResponse> responseObserver) {
        String requestString = request.getRequestString();
        System.out.println("requestString = " + requestString);

        this.grpcHouseKeepingService.cleanRooms();

        CleanResponse responseAdd = CleanResponse.newBuilder()
                .setResponseString("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
