package services;

import entities.HotelClient;
import entities.HotelClientContract;

import java.util.List;

public interface ClientService {
    void addClient(HotelClient client);
    void removeClient(HotelClient client);
    List<HotelClient> getClients();
    List<HotelClientContract> getContracts();
    void addContract(HotelClientContract contract); //not mapped by REST
    HotelClientContract getContractOfClient(HotelClient client);
}
