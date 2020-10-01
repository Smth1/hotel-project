package com.roma.distr.services;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.HotelClientContract;

import java.util.List;

public interface ClientService {
    void addClient(HotelClient client);
    void removeClient(HotelClient client);
    void removeContract(HotelClientContract contract);
    List<HotelClient> getClients();
    HotelClient getClientById(String id);
    List<HotelClientContract> getContracts();
    void addContract(HotelClientContract contract); //not mapped by REST
    HotelClientContract getContractOfClient(HotelClient client);
}
