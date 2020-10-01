package com.roma.distr.services.impl;

import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.entities.HotelClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.roma.distr.repository.HotelClientContractRepository;
import com.roma.distr.repository.HotelClientRepository;
import com.roma.distr.services.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private HotelClientRepository hotelClientRepository;

    @Autowired
    private HotelClientContractRepository hotelClientContractRepository;

    @Override
    public void addClient(HotelClient client) {
        System.out.println(client);
        hotelClientRepository.save(client);
    }

    @Override
    public void removeClient(HotelClient client) {
        hotelClientRepository.delete(client);
    }

    @Override
    public void removeContract(HotelClientContract contract) {
        hotelClientContractRepository.delete(contract);
    }

    @Override
    public List<HotelClient> getClients() {
        System.out.println(hotelClientRepository.findAll());
        return hotelClientRepository.findAll();

    }

    @Override
    public HotelClient getClientById(String id) {
        List<HotelClient> clients = hotelClientRepository.findAll();

        for (HotelClient client : clients) {
            if (client.getClientId().toString().equals(id)) {
                return client;
            }
        }

        return null;
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
            if (client.getClientId().equals(contract.getClient().getClientId()))
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
