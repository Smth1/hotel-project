package com.roma.distr.api.grpc;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.services.grpc.GrpcClientService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class ClientServiceGrpcImpl extends ClientServiceGrpc.ClientServiceImplBase {
    private final GrpcClientService grpcClientService;

    @Autowired
    public ClientServiceGrpcImpl(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @Override
    public void addClient(ClientRequestAdd request, StreamObserver<ClientResponseAdd> responseObserver) {
        ClientTransfer clientTransfer = request.getClient();

        HotelClient hotelClient = new HotelClient(clientTransfer.getName());

        this.grpcClientService.serveClient(hotelClient);

        ClientResponseAdd responseAdd = ClientResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }

    @Override
    public void getClients(ClientsRequestGet request, StreamObserver<ClientsResponseGet> responseObserver) {
        String getClientsRequest = request.getRequest();
        System.out.println("getClientsRequest = " + getClientsRequest);

        List<HotelClient> clients = this.grpcClientService.getClients();

        List<ClientTransfer> clientTransfers = clients.stream().map((el) -> ClientTransfer.newBuilder()
                .setId(el.getClientId().toString())
                .setName(el.getName())
                .build()).collect(Collectors.toList());

        ClientsResponseGet responseGet = ClientsResponseGet.newBuilder()
                .addAllClientsTransfer(clientTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteClient(ClientRequestDelete request, StreamObserver<ClientResponseDelete> responseObserver) {
        String clientId = request.getUuid();

        this.grpcClientService.moveOutClient(clientId);

        ClientResponseDelete responseDelete = ClientResponseDelete.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseDelete);
        responseObserver.onCompleted();
    }
}
