package com.roma.distr.api.grpc;

import com.roma.distr.entities.Administrator;
import com.roma.distr.services.AdminService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class AdminServiceGrpcImpl extends AdminServiceGrpc.AdminServiceImplBase {
    private final AdminService adminService;

    @Autowired
    public AdminServiceGrpcImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void getAdministrator(AdministratorRequestGet request, StreamObserver<AdministratorResponseGet> responseObserver) {
        String administratorRequest = request.getAdministratorRequest();

        Administrator administrator = adminService.getAdmin();

        AdministratorTransfer transfer = AdministratorTransfer.newBuilder()
                .setId(administrator.getId().toString())
                .setName(administrator.getName())
                .setAge(administrator.getAge())
                .setTelephoneNumber(administrator.getTelephoneNumber())
                .build();
        AdministratorResponseGet responseGet = AdministratorResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addAdministrator(AdministratorRequestAdd request, StreamObserver<AdministratorResponseAdd> responseObserver) {
        AdministratorTransfer admin = request.getAdmin();

        Administrator administrator = new Administrator(admin.getName(), admin.getAge(), admin.getTelephoneNumber());

        adminService.addEmployee(administrator);

        AdministratorResponseAdd responseAdd = AdministratorResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
