package com.roma.distr.api.grpc;

import com.roma.distr.dto.CleaningReportDTO;
import com.roma.distr.dto.CleaningReportsDTO;
import io.grpc.stub.StreamObserver;

import org.lognet.springboot.grpc.GRpcService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@GRpcService
public class HouseKeepingServiceImpl extends HouseKeepingServiceGrpc.HouseKeepingServiceImplBase {
    //private static final String URL = "http://housekeeping-service:8084";
    private static final String URL = "http://localhost:8084";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Override
    public void getAllReports(ReportsRequestGet request, StreamObserver<ReportsResponseGet> responseObserver) {
        String reportsRequest = request.getRequest();
        System.out.println("reportsRequest = " + reportsRequest);
        ResponseEntity<CleaningReportsDTO> responseEntity = restTemplate
                .exchange(URL + "/housekeeping/reports", HttpMethod.GET, headersEntity, CleaningReportsDTO.class);

        CleaningReportsDTO entityBody = responseEntity.getBody();

        Stream<CleaningReportDTO> stream = entityBody.getReports().stream();

        List<CleaningReportTransfer> contractTransfers = stream.map((el) -> CleaningReportTransfer.newBuilder()
                .setId(el.getId().toString())
                .setAdminId(el.getAdministratorId().toString())
                .setMaidId(el.getMaidId().toString())
                .setRooms(el.getRooms())
                .setCreationDate(el.getCreationDate())
                .build()).collect(Collectors.toList());

        ReportsResponseGet responseGet = ReportsResponseGet.newBuilder()
                .addAllCleaningReportTransfer(contractTransfers)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void clean(CleanRequest request, StreamObserver<CleanResponse> responseObserver) {
        String requestString = request.getRequestString();
        System.out.println("requestString = " + requestString);

        ResponseEntity<Void> responseEntity = restTemplate
                .exchange(URL + "/housekeeping/clean-rooms", HttpMethod.PUT, null, Void.class);

        CleanResponse responseAdd = CleanResponse.newBuilder()
                .setResponseString("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
