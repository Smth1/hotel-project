package com.roma.distr.services.grpc.impl;

import com.roma.distr.api.grpc.*;
import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.repository.HotelClientContractRepository;
import com.roma.distr.services.grpc.GrpcContractService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GrpcContractServiceImpl implements GrpcContractService {
    private final String URL = "localhost";
    private final ManagedChannel administrationManagedChannel;
    private final AdminServiceGrpc.AdminServiceBlockingStub adminServiceBlockingStub;
    private final CashierServiceGrpc.CashierServiceBlockingStub cashierServiceBlockingStub;
    private final PorterServiceGrpc.PorterServiceBlockingStub porterServiceBlockingStub;

    private HotelClientContractRepository hotelClientContractRepository;

    public GrpcContractServiceImpl(HotelClientContractRepository hotelClientContractRepository) {
        this.administrationManagedChannel = ManagedChannelBuilder.forAddress(URL, 6561)
                .usePlaintext().build();
        this.adminServiceBlockingStub = AdminServiceGrpc.newBlockingStub(administrationManagedChannel);
        this.cashierServiceBlockingStub = CashierServiceGrpc.newBlockingStub(administrationManagedChannel);
        this.porterServiceBlockingStub = PorterServiceGrpc.newBlockingStub(administrationManagedChannel);

        this.hotelClientContractRepository = hotelClientContractRepository;
    }


    @Override
    public void concludeContract(UUID client, UUID room) {
        AdministratorResponseGet get_administrator = this.adminServiceBlockingStub.getAdministrator(AdministratorRequestGet.newBuilder()
                .setAdministratorRequest("Get administrator")
                .build());

        CashierResponseGet get_cashier = this.cashierServiceBlockingStub.getCashier(CashierRequestGet.newBuilder()
                .setCashierRequest("Get cashier")
                .build());

        PorterResponseGet get_porter = this.porterServiceBlockingStub.getPorter(PorterRequestGet.newBuilder()
                .setPorterRequest("Get porter")
                .build());

        AdministratorTransfer administratorTransfer = get_administrator.getResult();
        CashierTransfer cashierTransfer = get_cashier.getResult();
        PorterTransfer porterTransfer = get_porter.getResult();

        UUID adminId = UUID.fromString(administratorTransfer.getId());
        UUID cashierId = UUID.fromString(cashierTransfer.getId());
        UUID porterId = UUID.fromString(porterTransfer.getId());

        HotelClientContract contract =
                new HotelClientContract.ContractBuilder().
                        addAdministrator(adminId).
                        addClient(client).
                        addRoom(room).
                        addCashier(cashierId).
                        addPorter(porterId).
                        createContract();
        this.addContract(contract);
    }

    private void addContract(HotelClientContract contract) {
        hotelClientContractRepository.save(contract);
    }

    @Override
    public List<HotelClientContract> getContracts() {
        return this.hotelClientContractRepository.findAll();
    }

    @Override
    public HotelClientContract getContractOfClient(UUID clientId) {
        List<HotelClientContract> contractList = this.hotelClientContractRepository.findAll();

        for (HotelClientContract contract : contractList) {
            if (contract.getClient().equals(clientId)) {
                return contract;
            }
        }

        return null;
    }

    @Override
    public void removeContract(UUID contractId) {
        HotelClientContract contract =
                this.hotelClientContractRepository.getOne(contractId);
        hotelClientContractRepository.delete(contract);
    }
}
