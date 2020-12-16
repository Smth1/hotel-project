package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class AdminServiceImpl extends AdminServiceGrpc.AdminServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final AdminServiceGrpc.AdminServiceBlockingStub adminServiceBlockingStub;

    public AdminServiceImpl() {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.adminServiceBlockingStub = AdminServiceGrpc.newBlockingStub(administrationManagedChannel);
    }

    @Override
    public void getAdministrator(AdministratorRequestGet request, StreamObserver<AdministratorResponseGet> responseObserver) {
        String administratorRequest = request.getAdministratorRequest();
        System.out.println("administratorRequest = " + administratorRequest);

        AdministratorResponseGet get_administrator = this.adminServiceBlockingStub.getAdministrator(AdministratorRequestGet.newBuilder()
                .setAdministratorRequest("Get administrator")
                .build());

        AdministratorTransfer transfer = get_administrator.getResult();

        AdministratorResponseGet responseGet = AdministratorResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addAdministrator(AdministratorRequestAdd request, StreamObserver<AdministratorResponseAdd> responseObserver) {
        AdministratorTransfer administratorTransfer = request.getAdmin();

        AdministratorResponseAdd responseAdd = this.adminServiceBlockingStub.addAdministrator(
                AdministratorRequestAdd.newBuilder()
                        .setAdmin(administratorTransfer)
                        .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
