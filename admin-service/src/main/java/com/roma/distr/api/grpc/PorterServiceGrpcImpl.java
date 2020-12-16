package com.roma.distr.api.grpc;

import com.roma.distr.entities.Porter;
import com.roma.distr.services.AdminService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class PorterServiceGrpcImpl extends PorterServiceGrpc.PorterServiceImplBase {
    private final AdminService adminService;

    @Autowired
    public PorterServiceGrpcImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void getPorter(PorterRequestGet request, StreamObserver<PorterResponseGet> responseObserver) {
        String porterRequest = request.getPorterRequest();
        System.out.println("porterRequest = " + porterRequest);

        Porter randomPorter = adminService.getRandomPorter();

        PorterTransfer transfer = PorterTransfer.newBuilder()
                .setId(randomPorter.getId().toString())
                .setName(randomPorter.getName())
                .setAge(randomPorter.getAge())
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

        Porter porter = new Porter(porterTransfer.getName(), porterTransfer.getAge());

        adminService.addEmployee(porter);

        PorterResponseAdd responseAdd = PorterResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
