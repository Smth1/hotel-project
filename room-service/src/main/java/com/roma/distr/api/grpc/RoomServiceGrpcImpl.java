package com.roma.distr.api.grpc;

import com.roma.distr.entities.Room;
import com.roma.distr.services.RoomService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class RoomServiceGrpcImpl extends RoomServiceGrpc.RoomServiceImplBase {
    private final RoomService roomService;

    @Autowired
    public RoomServiceGrpcImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void addRoom(RoomRequestAdd request, StreamObserver<RoomResponseAdd> responseObserver) {
        RoomTransfer roomTransfer = request.getRoom();

        Room room = new Room(roomTransfer.getNumber(), roomTransfer.getClientNumber());

        roomService.addRoom(room);

        RoomResponseAdd responseAdd = RoomResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }

    @Override
    public void getAvailableRooms(RoomsRequestGet request, StreamObserver<RoomsResponseGet> responseObserver) {
        String roomsRequest = request.getRequest();
        System.out.println("roomsRequest = " + roomsRequest);

        Stream<Room> stream = roomService.getAvailableRooms().stream();

        List<RoomTransfer> roomTransfers = stream.map((el) -> RoomTransfer.newBuilder()
                .setId(el.getId().toString())
                .setNumber(el.getNumber())
                .setClientNumber(el.getClientNumber())
                .setIsClean(el.isClean())
                .setIsFree(el.isFree())
                .build()).collect(Collectors.toList());

        RoomsResponseGet responseGet = RoomsResponseGet.newBuilder()
                .addAllRoomsTransfer(roomTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void getUncleanRooms(RoomsRequestGet request, StreamObserver<RoomsResponseGet> responseObserver) {
        String uncleanRoomsRequest = request.getRequest();
        System.out.println("uncleanRoomsRequest = " + uncleanRoomsRequest);

        List<RoomTransfer> roomTransfers = this.roomService.getUncleanRooms().stream().map((el) ->
                RoomTransfer.newBuilder()
                        .setId(el.getId().toString())
                        .setNumber(el.getNumber())
                        .setClientNumber(el.getClientNumber())
                        .setIsClean(el.isClean())
                        .setIsFree(el.isFree())
                        .build()).collect(Collectors.toList());

        RoomsResponseGet responseGet = RoomsResponseGet.newBuilder()
                .addAllRoomsTransfer(roomTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void makeClean(MakeCleanRequest request, StreamObserver<MakeCleanResponse> responseObserver) {
        String roomId = request.getRoomId();
        System.out.println("roomId = " + roomId);

        this.roomService.setClean(UUID.fromString(roomId));

        MakeCleanResponse makeCleanResponse = MakeCleanResponse.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(makeCleanResponse);
        responseObserver.onCompleted();
    }
}
