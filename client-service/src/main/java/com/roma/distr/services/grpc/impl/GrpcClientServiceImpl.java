package com.roma.distr.services.grpc.impl;

import com.roma.distr.api.grpc.*;
import com.roma.distr.entities.HotelClient;
import com.roma.distr.repository.HotelClientRepository;
import com.roma.distr.services.grpc.GrpcClientService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GrpcClientServiceImpl implements GrpcClientService {
    private final String URL = "localhost";
    private final ManagedChannel contractManagedChannel;
    private final ManagedChannel roomManagedChannel;
    private final ContractServiceGrpc.ContractServiceBlockingStub contractServiceBlockingStub;
    private final RoomServiceGrpc.RoomServiceBlockingStub roomServiceBlockingStub;

    private HotelClientRepository hotelClientRepository;

    @Autowired
    public GrpcClientServiceImpl(HotelClientRepository hotelClientRepository) {
        this.contractManagedChannel = ManagedChannelBuilder.forAddress(URL, 6563)
                .usePlaintext().build();
        this.roomManagedChannel = ManagedChannelBuilder.forAddress(URL, 6565)
                .usePlaintext().build();
        this.contractServiceBlockingStub = ContractServiceGrpc.newBlockingStub(contractManagedChannel);
        this.roomServiceBlockingStub = RoomServiceGrpc.newBlockingStub(roomManagedChannel);

        this.hotelClientRepository = hotelClientRepository;
    }

    private void addClient(HotelClient client) {
        System.out.println(client);
        hotelClientRepository.save(client);
    }

    private void removeClient(HotelClient client) {
        hotelClientRepository.delete(client);
    }

    @Override
    public List<HotelClient> getClients() {
        System.out.println(hotelClientRepository.findAll());
        return hotelClientRepository.findAll();
    }

    @Override
    public HotelClient getClientById(String id) {
        List<HotelClient> clients = hotelClientRepository.findAll();

        for (HotelClient client : clients) {
            if (client.getClientId().toString().equals(id)) {
                return client;
            }
        }

        return null;
    }

    @Override
    public void serveClient(HotelClient client) {
        List<UUID> rooms = this.getAvailableRooms();
        Random random = new Random();
        System.out.println(rooms.size());
        int index = Math.abs(random.nextInt(rooms.size()));

        System.out.println(index);
        UUID room = rooms.get(index);
        client.setRoom(room);

        this.addClient(client);

        this.concludeContract(client,room);
    }

    @Override
    public void moveOutClient(String clientId) {
        HotelClient client = this.getClientById(clientId);

        if (client != null) {
            UUID contract = this.getContractOfClient(client.getClientId());
            this.removeContract(contract);
            this.removeClient(client);
        }
        else {
            System.out.println("Client id " + clientId + " not found.");
        }
    }

    private void concludeContract(HotelClient client, UUID roomId) {
        CreateContractResponse contractResponse = this.contractServiceBlockingStub.createContract(CreateContractRequest.newBuilder()
                .setClientId(client.getClientId().toString())
                .setRoomId(roomId.toString())
                .build());
    }

    private List<UUID> getAvailableRooms() {
        RoomsResponseGet get_available_rooms = this.roomServiceBlockingStub.getAvailableRooms(RoomsRequestGet.newBuilder()
                .setRequest("Get available rooms")
                .build());
        List<UUID> rooms = get_available_rooms.getRoomsTransferList()
                .stream().map((el) ->
                        UUID.fromString(el.getId())).collect(Collectors.toList());

        return rooms;
    }

    private UUID getContractOfClient(UUID clientId) {
        ContractResponseGet contractOfClient = this.contractServiceBlockingStub.getContractOfClient(ContractRequestGet.newBuilder()
                .setClientId(clientId.toString())
                .build());

        ContractTransfer contractTransfer = contractOfClient.getContractTransfer();

        UUID contractId = UUID.fromString(contractTransfer.getId());

        return contractId;
    }

    private void removeContract(UUID contractId) {
        RemoveContractResponse removeContractResponse = this.contractServiceBlockingStub.removeContract(RemoveContractRequest.newBuilder()
                .setContractId(contractId.toString())
                .build());

        String response = removeContractResponse.getResponse();
        System.out.println("response = " + response);
    }
}
