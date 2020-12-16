package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class RoomServiceImpl extends RoomServiceGrpc.RoomServiceImplBase{
    private final String URL = "localhost";
    private final ManagedChannel managedChannel;
    private final RoomServiceGrpc.RoomServiceBlockingStub roomServiceBlockingStub;

    public RoomServiceImpl() {
        this.managedChannel = ManagedChannelBuilder.forAddress(URL, 6565)
                .usePlaintext().build();
        this.roomServiceBlockingStub = RoomServiceGrpc.newBlockingStub(managedChannel);
    }

    @Override
    public void addRoom(RoomRequestAdd request, StreamObserver<RoomResponseAdd> responseObserver) {
        RoomTransfer roomTransfer = request.getRoom();

        RoomResponseAdd responseAdd = this.roomServiceBlockingStub.addRoom(RoomRequestAdd.newBuilder()
                .setRoom(roomTransfer)
                .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }

    @Override
    public void getAvailableRooms(RoomsRequestGet request, StreamObserver<RoomsResponseGet> responseObserver) {
        String roomsRequest = request.getRequest();
        System.out.println("roomsRequest = " + roomsRequest);

        RoomsResponseGet responseGet = this.roomServiceBlockingStub.getAvailableRooms(RoomsRequestGet.newBuilder()
                .setRequest(roomsRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }
}
