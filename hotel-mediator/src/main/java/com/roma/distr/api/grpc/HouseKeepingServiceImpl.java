package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HouseKeepingServiceImpl extends HouseKeepingServiceGrpc.HouseKeepingServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel managedChannel;
    private final HouseKeepingServiceGrpc.HouseKeepingServiceBlockingStub houseKeepingServiceBlockingStub;

    public HouseKeepingServiceImpl() {
        this.managedChannel = ManagedChannelBuilder.forAddress(URL, 6564)
                .usePlaintext().build();
        this.houseKeepingServiceBlockingStub = HouseKeepingServiceGrpc.newBlockingStub(managedChannel);
    }

    @Override
    public void getAllReports(ReportsRequestGet request, StreamObserver<ReportsResponseGet> responseObserver) {
        String reportsRequest = request.getRequest();
        System.out.println("reportsRequest = " + reportsRequest);

        ReportsResponseGet responseGet = this.houseKeepingServiceBlockingStub.getAllReports(ReportsRequestGet.newBuilder()
                .setRequest(reportsRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void clean(CleanRequest request, StreamObserver<CleanResponse> responseObserver) {
        String requestString = request.getRequestString();
        System.out.println("requestString = " + requestString);

        CleanResponse cleanResponse = this.houseKeepingServiceBlockingStub.clean(CleanRequest.newBuilder()
                .setRequestString(requestString)
                .build());

        responseObserver.onNext(cleanResponse);
        responseObserver.onCompleted();
    }
}
