package com.roma.distr.api.grpc;

import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.services.grpc.GrpcContractService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class ContractServiceGrpcImpl extends ContractServiceGrpc.ContractServiceImplBase {
    private final GrpcContractService grpcContractService;

    @Autowired
    public ContractServiceGrpcImpl(GrpcContractService grpcContractService) {
        this.grpcContractService = grpcContractService;
    }

    @Override
    public void getAllContracts(ContractsRequestGet request, StreamObserver<ContractsResponseGet> responseObserver) {
        String getContractsRequest = request.getRequest();
        System.out.println("getContractsRequest = " + getContractsRequest);

        List<HotelClientContract> contracts = this.grpcContractService.getContracts();

        List<ContractTransfer> contractTransfers = contracts.stream().map((el) -> ContractTransfer.newBuilder()
                .setId(el.getId().toString())
                .setHotelAdmin(el.getHotelAdmin().toString())
                .setClient(el.getClient().toString())
                .setCashier(el.getCashier().toString())
                .setPorter(el.getPorter().toString())
                .setRoom(el.getRoom().toString())
                .setCreationDate(el.getCreationDate())
                .setIsOpen(el.isOpen())
                .build()).collect(Collectors.toList());

        ContractsResponseGet responseGet = ContractsResponseGet.newBuilder()
                .addAllContractsTransfer(contractTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void getContractOfClient(ContractRequestGet request, StreamObserver<ContractResponseGet> responseObserver) {
        String clientId = request.getClientId();

        HotelClientContract contractOfClient = this.grpcContractService.getContractOfClient(UUID.fromString(clientId));

        ContractTransfer contractTransfer = ContractTransfer.newBuilder()
                .setId(contractOfClient.getId().toString())
                .setHotelAdmin(contractOfClient.getHotelAdmin().toString())
                .setClient(contractOfClient.getClient().toString())
                .setCashier(contractOfClient.getCashier().toString())
                .setPorter(contractOfClient.getPorter().toString())
                .setRoom(contractOfClient.getRoom().toString())
                .setCreationDate(contractOfClient.getCreationDate())
                .build();

        ContractResponseGet contractResponseGet = ContractResponseGet.newBuilder()
                .setContractTransfer(contractTransfer)
                .build();

        responseObserver.onNext(contractResponseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void createContract(CreateContractRequest request, StreamObserver<CreateContractResponse> responseObserver) {
        String clientId = request.getClientId();
        String roomId = request.getRoomId();

        this.grpcContractService.concludeContract(UUID.fromString(clientId), UUID.fromString(roomId));

        CreateContractResponse contractResponse = CreateContractResponse.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(contractResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void removeContract(RemoveContractRequest request, StreamObserver<RemoveContractResponse> responseObserver) {
        String contractId = request.getContractId();

        this.grpcContractService.removeContract(UUID.fromString(contractId));

        RemoveContractResponse contractResponse = RemoveContractResponse.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(contractResponse);
        responseObserver.onCompleted();
    }
}
