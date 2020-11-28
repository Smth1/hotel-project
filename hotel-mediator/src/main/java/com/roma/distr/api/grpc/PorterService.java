package com.roma.distr.api.grpc;

import com.roma.distr.dto.MaidDTO;
import com.roma.distr.dto.PorterDTO;
import io.grpc.stub.StreamObserver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PorterService extends PorterServiceGrpc.PorterServiceImplBase {
    //private static final String URL = "http://admin-service:8081";
    private static final String URL = "http://localhost:8081";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getPorter(PorterRequestGet request, StreamObserver<PorterResponseGet> responseObserver) {
        String porterRequest = request.getPorterRequest();
        System.out.println("porterRequest = " + porterRequest);

        ResponseEntity<PorterDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/porter", HttpMethod.GET, headersEntity, PorterDTO.class);
        PorterDTO body = responseEntity.getBody();

        assert body != null;
        PorterTransfer transfer = PorterTransfer.newBuilder()
                .setName(body.getName())
                .setAge(body.getAge())
                .build();

        PorterResponseGet responseGet = PorterResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addPorter(PorterRequestAdd request, StreamObserver<PorterResponseAdd> responseObserver) {
        PorterTransfer porterTransfer = request.getPorter();
        PorterDTO porterDTO = PorterDTO.builder()
                .age(porterTransfer.getAge())
                .name(porterTransfer.getName())
                .build();
        HttpEntity<PorterDTO> porterDTOEntity = new HttpEntity<>(porterDTO);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/porter", HttpMethod.POST, porterDTOEntity, Void.class);

        PorterResponseAdd responseAdd = PorterResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
