package com.roma.distr.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class CashierService extends CashierServiceGrpc.CashierServiceImplBase {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final CashierServiceGrpc.CashierServiceBlockingStub cashierServiceBlockingStub;

    public CashierService() {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.cashierServiceBlockingStub = CashierServiceGrpc.newBlockingStub(administrationManagedChannel);
    }

    @Override
    public void getCashier(CashierRequestGet request, StreamObserver<CashierResponseGet> responseObserver) {
        String cashierRequest = request.getCashierRequest();
        System.out.println("cashierRequest = " + cashierRequest);

        CashierResponseGet get_cashier = this.cashierServiceBlockingStub.getCashier(CashierRequestGet.newBuilder()
                .setCashierRequest("Get cashier")
                .build());

        CashierTransfer transfer = get_cashier.getResult();

        CashierResponseGet responseGet = CashierResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addCashier(CashierRequestAdd request, StreamObserver<CashierResponseAdd> responseObserver) {
        CashierTransfer cashierTransfer = request.getCashier();

        CashierResponseAdd responseAdd = this.cashierServiceBlockingStub.addCashier(CashierRequestAdd.newBuilder()
                .setCashier(cashierTransfer)
                .build());

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
