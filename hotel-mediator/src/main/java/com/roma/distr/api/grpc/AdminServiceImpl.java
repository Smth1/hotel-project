package com.roma.distr.api.grpc;

import com.roma.distr.dto.AdministratorDTO;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@GRpcService
public class AdminServiceImpl extends AdminServiceGrpc.AdminServiceImplBase {
    //private static final String URL = "http://admin-service:8081";
    private static final String URL = "http://localhost:8081";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getAdministrator(AdministratorRequestGet request, StreamObserver<AdministratorResponseGet> responseObserver) {
        String administratorRequest = request.getAdministratorRequest();
        ResponseEntity<AdministratorDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.GET, headersEntity, AdministratorDTO.class);
        AdministratorDTO body = responseEntity.getBody();

        assert body != null;
        AdministratorTransfer transfer = AdministratorTransfer.newBuilder()
                .setId(body.getId())
                .setName(body.getName())
                .setAge(body.getAge())
                .setTelephoneNumber(body.getTelephoneNumber())
                .build();

        AdministratorResponseGet responseGet = AdministratorResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addAdministrator(AdministratorRequestAdd request, StreamObserver<AdministratorResponseAdd> responseObserver) {
        AdministratorTransfer administratorTransfer = request.getAdmin();
        AdministratorDTO administratorDTO = AdministratorDTO.builder()
                .age(administratorTransfer.getAge())
                .name(administratorTransfer.getName())
                .telephoneNumber(administratorTransfer.getTelephoneNumber())
                .build();
        HttpEntity<AdministratorDTO> administratorDTOEntity = new HttpEntity<>(administratorDTO);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/admin", HttpMethod.POST, administratorDTOEntity, Void.class);

        AdministratorResponseAdd responseAdd = AdministratorResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
