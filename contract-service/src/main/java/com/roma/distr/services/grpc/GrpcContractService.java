package com.roma.distr.services.grpc;

import com.roma.distr.entities.HotelClientContract;

import java.util.List;
import java.util.UUID;

public interface GrpcContractService {
    void concludeContract(UUID client, UUID room);

    List<HotelClientContract> getContracts();

    HotelClientContract getContractOfClient(UUID client);

    void removeContract(UUID contractId);
}
