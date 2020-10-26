package com.roma.distr.services;

import com.roma.distr.entities.HotelClientContract;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    void concludeContract(UUID client, UUID room);

    List<HotelClientContract> getContracts();

    HotelClientContract getContractOfClient(UUID client);

    void removeContract(UUID contractId);
}
