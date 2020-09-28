package services.impl;

import entities.HotelClientContract;
import entities.HotelClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HotelClientContractRepository;
import repository.HotelClientRepository;
import services.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private HotelClientRepository hotelClientRepository;

    @Autowired
    private HotelClientContractRepository hotelClientContractRepository;

    @Override
    public void addClient(HotelClient client) {
        hotelClientRepository.save(client);
    }

    @Override
    public void removeClient(HotelClient client) {
        hotelClientRepository.delete(client);
    }

    @Override
    public List<HotelClient> getClients() {
        return hotelClientRepository.findAll();
    }

    @Override
    public List<HotelClientContract> getContracts() {
        return hotelClientContractRepository.findAll();
    }

    @Override
    public void addContract(HotelClientContract contract) {
        hotelClientContractRepository.save(contract);
    }

    @Override
    public HotelClientContract getContractOfClient(HotelClient client) {
        List<HotelClientContract> contracts = hotelClientContractRepository.findAll();

        for (HotelClientContract contract : contracts) {
            if (client.equals(contract.getClient()))
                return contract;
        }
        return null;
    }

    @Override
    public String toString() {
        return "ClientService{" +
                "clients=" + this.getClients() +
                '}';
    }
}
