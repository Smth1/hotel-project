package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class PorterService extends PorterServiceGrpc.PorterServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final PorterServiceGrpc.PorterServiceBlockingStub porterServiceBlockingStub;

    public PorterService() {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.porterServiceBlockingStub = PorterServiceGrpc.newBlockingStub(administrationManagedChannel);
    }

    @Override
    public void getPorter(PorterRequestGet request, StreamObserver<PorterResponseGet> responseObserver) {
        String porterRequest = request.getPorterRequest();
        System.out.println("porterRequest = " + porterRequest);

        PorterResponseGet responseGet = this.porterServiceBlockingStub.getPorter(PorterRequestGet.newBuilder()
                .setPorterRequest(porterRequest)
                .build());

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addPorter(PorterRequestAdd request, StreamObserver<PorterResponseAdd> responseObserver) {
        PorterTransfer porterTransfer = request.getPorter();

        PorterResponseAdd responseAdd = this.porterServiceBlockingStub.addPorter(PorterRequestAdd.newBuilder()
                .setPorter(porterTransfer)
                .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
