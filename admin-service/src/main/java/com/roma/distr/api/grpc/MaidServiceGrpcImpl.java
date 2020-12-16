package com.roma.distr.api.grpc;

import com.roma.distr.entities.Maid;
import com.roma.distr.services.AdminService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class MaidServiceGrpcImpl extends MaidServiceGrpc.MaidServiceImplBase {
    private final AdminService adminService;

    @Autowired
    public MaidServiceGrpcImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void getMaid(MaidRequestGet request, StreamObserver<MaidResponseGet> responseObserver) {
        String maidRequest = request.getMaidRequest();
        System.out.println("maidRequest = " + maidRequest);

        Maid randomMaid = adminService.getRandomMaid();

        MaidTransfer transfer = MaidTransfer.newBuilder()
                .setId(randomMaid.getId().toString())
                .setName(randomMaid.getName())
                .setAge(randomMaid.getAge())
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

        Maid maid = new Maid(maidTransfer.getName(), maidTransfer.getAge());

        adminService.addEmployee(maid);

        MaidResponseAdd responseAdd = MaidResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
