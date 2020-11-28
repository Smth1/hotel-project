package com.roma.distr.api.grpc;

import com.roma.distr.dto.RoomDTO;
import com.roma.distr.dto.RoomsDTO;
import io.grpc.stub.StreamObserver;

import org.lognet.springboot.grpc.GRpcService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class RoomServiceImpl extends RoomServiceGrpc.RoomServiceImplBase{
    //private static final String URL = "http://room-service:8085";
    private static final String URL = "http://localhost:8085";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void addRoom(RoomRequestAdd request, StreamObserver<RoomResponseAdd> responseObserver) {
        RoomTransfer clientTransfer = request.getRoom();

        RoomDTO roomDTO = RoomDTO.builder()
                .number(clientTransfer.getNumber())
                .clientNumber(clientTransfer.getClientNumber())
                .build();

        HttpEntity<RoomDTO> deliverRoom = new HttpEntity<>(roomDTO, headers);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/room-service/room", HttpMethod.POST, deliverRoom, Void.class);

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

        ResponseEntity<RoomsDTO> responseEntity = restTemplate
                .exchange(URL + "/room-service/rooms", HttpMethod.GET, headersEntity, RoomsDTO.class);

        RoomsDTO entityBody = responseEntity.getBody();

        assert entityBody != null;
        Stream<RoomDTO> stream = entityBody.getRooms().stream();

        List<RoomTransfer> roomTransfers = stream.map((el) -> RoomTransfer.newBuilder()
                .setNumber(el.getNumber())
                .setClientNumber(el.getClientNumber())
                .build()).collect(Collectors.toList());

        RoomsResponseGet responseGet = RoomsResponseGet.newBuilder()
                .addAllRoomsTransfer(roomTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }
}
