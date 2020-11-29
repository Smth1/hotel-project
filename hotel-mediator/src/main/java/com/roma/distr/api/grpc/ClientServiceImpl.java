package com.roma.distr.api.grpc;

import com.roma.distr.dto.AdministratorDTO;
import com.roma.distr.dto.ClientsDTO;
import com.roma.distr.dto.HotelClientDTO;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class ClientServiceImpl extends ClientServiceGrpc.ClientServiceImplBase {
    //private static final String URL = "http://client-service:8082";
    private static final String URL = "http://localhost:8082";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void addClient(ClientRequestAdd request, StreamObserver<ClientResponseAdd> responseObserver) {
        ClientTransfer clientTransfer = request.getClient();
        HotelClientDTO hotelClientDTO = HotelClientDTO.builder()
                .clientName(clientTransfer.getName())
                .build();

        HttpEntity<HotelClientDTO> hotelClientDTOEntity = new HttpEntity<>(hotelClientDTO);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/client", HttpMethod.POST, hotelClientDTOEntity, Void.class);

        ClientResponseAdd responseAdd = ClientResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }

    @Override
    public void getClients(ClientsRequestGet request, StreamObserver<ClientsResponseGet> responseObserver) {
        String clientsRequest = request.getRequest();
        System.out.println("clientsRequest = " + clientsRequest);

        ResponseEntity<ClientsDTO> responseEntity = restTemplate
                .exchange(URL + "/clients", HttpMethod.GET, headersEntity, ClientsDTO.class);
        ClientsDTO entityBody = responseEntity.getBody();

        assert entityBody != null;
        Stream<HotelClientDTO> stream = entityBody.getClients().stream();

        List<ClientTransfer> clientTransfers = stream.map((el) -> ClientTransfer.newBuilder()
                .setId(el.getClientId().toString())
                .setName(el.getClientName())
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

        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/clients/" + clientId,
                        HttpMethod.DELETE, null, Void.class);

        ClientResponseDelete responseDelete = ClientResponseDelete.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseDelete);
        responseObserver.onCompleted();
    }
}
