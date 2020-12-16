package com.roma.distr.api.grpc;

import com.roma.distr.entities.Cashier;
import com.roma.distr.services.AdminService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class CashierServiceGrpcImpl extends CashierServiceGrpc.CashierServiceImplBase {
    private final AdminService adminService;

    @Autowired
    public CashierServiceGrpcImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void getCashier(CashierRequestGet request, StreamObserver<CashierResponseGet> responseObserver) {
        String cashierRequest = request.getCashierRequest();
        System.out.println("cashierRequest = " + cashierRequest);

        Cashier randomCashier = adminService.getRandomCashier();

        CashierTransfer transfer = CashierTransfer.newBuilder()
                .setId(randomCashier.getId().toString())
                .setName(randomCashier.getName())
                .setAge(randomCashier.getAge())
                .build();

        CashierResponseGet responseGet = CashierResponseGet.newBuilder()
                .setResult(transfer)
                .build();

        responseObserver.onNext(responseGet);
        responseObserver.onCompleted();
    }

    @Override
    public void addCashier(CashierRequestAdd request, StreamObserver<CashierResponseAdd> responseObserver) {
        CashierTransfer cashierTransfer = request.getCashier();

        Cashier cashier = new Cashier(cashierTransfer.getName(), cashierTransfer.getAge());

        adminService.addEmployee(cashier);

        CashierResponseAdd responseAdd = CashierResponseAdd.newBuilder()
                .setResponse("success")
                .build();

        responseObserver.onNext(responseAdd);
        responseObserver.onCompleted();
    }
}
