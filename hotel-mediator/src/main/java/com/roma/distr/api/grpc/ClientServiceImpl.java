package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class ClientServiceImpl extends ClientServiceGrpc.ClientServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel managedChannel;
    private final ClientServiceGrpc.ClientServiceBlockingStub clientServiceBlockingStub;

    public ClientServiceImpl() {
        this.managedChannel = ManagedChannelBuilder.forAddress(URL, 6562)
                .usePlaintext().build();
        this.clientServiceBlockingStub = ClientServiceGrpc.newBlockingStub(managedChannel);
    }

    @Override
    public void addClient(ClientRequestAdd request, StreamObserver<ClientResponseAdd> responseObserver) {
        ClientTransfer clientTransfer = request.getClient();

        ClientResponseAdd responseAdd = this.clientServiceBlockingStub.addClient(ClientRequestAdd.newBuilder()
                .setClient(clientTransfer)
                .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }

    @Override
    public void getClients(ClientsRequestGet request, StreamObserver<ClientsResponseGet> responseObserver) {
        String clientsRequest = request.getRequest();
        System.out.println("clientsRequest = " + clientsRequest);

        ClientsResponseGet responseGet = this.clientServiceBlockingStub.getClients(ClientsRequestGet.newBuilder()
                .setRequest(clientsRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteClient(ClientRequestDelete request, StreamObserver<ClientResponseDelete> responseObserver) {
        String clientId = request.getClientId();

        ClientResponseDelete responseDelete = this.clientServiceBlockingStub.deleteClient(ClientRequestDelete.newBuilder()
                .setClientId(clientId)
                .build());

        responseObserver.onNext(responseDelete);
        responseObserver.onCompleted();
    }
}
