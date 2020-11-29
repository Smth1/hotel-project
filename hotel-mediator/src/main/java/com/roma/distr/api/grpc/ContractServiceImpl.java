package com.roma.distr.api.grpc;

import com.roma.distr.dto.ClientsDTO;
import com.roma.distr.dto.ContractsDTO;
import com.roma.distr.dto.HotelClientContractDTO;
import com.roma.distr.dto.HotelClientDTO;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class ContractServiceImpl extends ContractServiceGrpc.ContractServiceImplBase {
    //private static final String URL = "http://contract-service:8083";
    private static final String URL = "http://localhost:8083";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getAllContracts(ContractsRequestGet request, StreamObserver<ContractsResponseGet> responseObserver) {
        String contractsRequest = request.getRequest();
        System.out.println("contractsRequest = " + contractsRequest);

        ResponseEntity<ContractsDTO> responseEntity = restTemplate
                .exchange(URL + "/contracts", HttpMethod.GET, headersEntity, ContractsDTO.class);

        ContractsDTO entityBody = responseEntity.getBody();

        Stream<HotelClientContractDTO> stream = entityBody.getContracts().stream();

        List<ContractTransfer> contractTransfers = stream.map((el) -> ContractTransfer.newBuilder()
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
        ResponseEntity<HotelClientContractDTO> responseEntity = restTemplate
                .exchange(URL + "/contract?clientId=" + clientId, HttpMethod.GET, headersEntity, HotelClientContractDTO.class);

        HotelClientContractDTO contractDTO = responseEntity.getBody();

        ContractTransfer contractTransfer = ContractTransfer.newBuilder()
                .setId(contractDTO.getId().toString())
                .setHotelAdmin(contractDTO.getHotelAdmin().toString())
                .setClient(contractDTO.getClient().toString())
                .setCashier(contractDTO.getCashier().toString())
                .setPorter(contractDTO.getPorter().toString())
                .setRoom(contractDTO.getRoom().toString())
                .setCreationDate(contractDTO.getCreationDate())
                .setClosingDate(contractDTO.getClosingDate())
                .setIsOpen(contractDTO.isOpen())
                .build();

        ContractResponseGet contractResponseGet = ContractResponseGet.newBuilder()
                .setContractTransfer(contractTransfer)
                .build();

        responseObserver.onNext(contractResponseGet);
        responseObserver.onCompleted();
    }
}
