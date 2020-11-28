package com.roma.distr.api.grpc;

import com.roma.distr.dto.CashierDTO;
import com.roma.distr.dto.MaidDTO;
import io.grpc.stub.StreamObserver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MaidService extends MaidServiceGrpc.MaidServiceImplBase {
    //private static final String URL = "http://admin-service:8081";
    private static final String URL = "http://localhost:8081";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getMaid(MaidRequestGet request, StreamObserver<MaidResponseGet> responseObserver) {
        String maidRequest = request.getMaidRequest();
        System.out.println("maidRequest = " + maidRequest);

        ResponseEntity<MaidDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/maid", HttpMethod.GET, headersEntity, MaidDTO.class);
        MaidDTO body = responseEntity.getBody();

        assert body != null;
        MaidTransfer transfer = MaidTransfer.newBuilder()
                .setName(body.getName())
                .setAge(body.getAge())
                .build();

        MaidResponseGet responseGet = MaidResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addMaid(MaidRequestAdd request, StreamObserver<MaidResponseAdd> responseObserver) {
        MaidTransfer maidTransfer = request.getMaid();
        MaidDTO maidDTO = MaidDTO.builder()
                .age(maidTransfer.getAge())
                .name(maidTransfer.getName())
                .build();
        HttpEntity<MaidDTO> maidDTOEntity = new HttpEntity<>(maidDTO);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/maid", HttpMethod.POST, maidDTOEntity, Void.class);

        MaidResponseAdd responseAdd = MaidResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
