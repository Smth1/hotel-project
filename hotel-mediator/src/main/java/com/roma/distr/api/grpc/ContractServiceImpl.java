package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;


@GRpcService
public class ContractServiceImpl extends ContractServiceGrpc.ContractServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel managedChannel;
    private final ContractServiceGrpc.ContractServiceBlockingStub contractServiceBlockingStub;

    public ContractServiceImpl() {
        this.managedChannel = ManagedChannelBuilder.forAddress(URL, 6563)
                .usePlaintext().build();
        this.contractServiceBlockingStub = ContractServiceGrpc.newBlockingStub(managedChannel);
    }

    @Override
    public void getAllContracts(ContractsRequestGet request, StreamObserver<ContractsResponseGet> responseObserver) {
        String contractsRequest = request.getRequest();
        System.out.println("contractsRequest = " + contractsRequest);

        ContractsResponseGet responseGet = this.contractServiceBlockingStub.getAllContracts(ContractsRequestGet.newBuilder()
                .setRequest(contractsRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void getContractOfClient(ContractRequestGet request, StreamObserver<ContractResponseGet> responseObserver) {
        String clientId = request.getClientId();

        ContractResponseGet contractResponseGet = this.contractServiceBlockingStub.getContractOfClient(ContractRequestGet.newBuilder()
                .setClientId(clientId)
                .build());

        responseObserver.onNext(contractResponseGet);
        responseObserver.onCompleted();
    }
}
