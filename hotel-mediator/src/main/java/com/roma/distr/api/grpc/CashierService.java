package com.roma.distr.api.grpc;

import com.roma.distr.dto.AdministratorDTO;
import com.roma.distr.dto.CashierDTO;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@GRpcService
public class CashierService extends CashierServiceGrpc.CashierServiceImplBase {
    //private static final String URL = "http://admin-service:8081";
    private static final String URL = "http://localhost:8081";

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getCashier(CashierRequestGet request, StreamObserver<CashierResponseGet> responseObserver) {
        String cashierRequest = request.getCashierRequest();
        System.out.println("cashierRequest = " + cashierRequest);

        ResponseEntity<CashierDTO> responseEntity = restTemplate
                .exchange(URL + "/administration/cashier", HttpMethod.GET, headersEntity, CashierDTO.class);
        CashierDTO body = responseEntity.getBody();

        assert body != null;
        CashierTransfer transfer = CashierTransfer.newBuilder()
                .setName(body.getName())
                .setAge(body.getAge())
                .build();

        CashierResponseGet responseGet = CashierResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addCashier(CashierRequestAdd request, StreamObserver<CashierResponseAdd> responseObserver) {
        CashierTransfer administratorTransfer = request.getCashier();
        CashierDTO cashierDTO = CashierDTO.builder()
                .age(administratorTransfer.getAge())
                .name(administratorTransfer.getName())
                .build();
        HttpEntity<CashierDTO> cashierDTOEntity = new HttpEntity<>(cashierDTO);
        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/administration/cashier", HttpMethod.POST, cashierDTOEntity, Void.class);

        CashierResponseAdd responseAdd = CashierResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
