package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class MaidService extends MaidServiceGrpc.MaidServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final MaidServiceGrpc.MaidServiceBlockingStub maidServiceBlockingStub;

    public MaidService() {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.maidServiceBlockingStub = MaidServiceGrpc.newBlockingStub(administrationManagedChannel);
    }

    @Override
    public void getMaid(MaidRequestGet request, StreamObserver<MaidResponseGet> responseObserver) {
        String maidRequest = request.getMaidRequest();
        System.out.println("maidRequest = " + maidRequest);

        MaidResponseGet responseGet = this.maidServiceBlockingStub.getMaid(MaidRequestGet.newBuilder()
                .setMaidRequest(maidRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addMaid(MaidRequestAdd request, StreamObserver<MaidResponseAdd> responseObserver) {
        MaidTransfer maidTransfer = request.getMaid();

        MaidResponseAdd responseAdd = this.maidServiceBlockingStub.addMaid(MaidRequestAdd.newBuilder()
                .setMaid(maidTransfer)
                .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
