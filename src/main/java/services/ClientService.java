package services;

import entities.HotelClientContract;
import entities.HotelClient;

import java.util.ArrayList;
import java.util.List;

public final class ClientService {
    private final List<HotelClient> clients;
    private final List<HotelClientContract> contracts;
    private final List<HotelClient> blackList;

    public ClientService() {
        clients = new ArrayList<>();
        contracts = new ArrayList<>();
        blackList = new ArrayList<>();

        System.out.println("Created client service");
    }

    public void addClient(HotelClient client) {
        clients.add(client);

        System.out.println("Client " + client.getName() + " registered");
    }

    public void removeClient(HotelClient client) {
        clients.remove(client);
    }

    public void addClientToBlackList(HotelClient client) {
        blackList.add(client);
    }

    public List<HotelClient> getClients() {
        return clients;
    }

    public List<HotelClientContract> getContracts() {
        return contracts;
    }

    public void addContract(HotelClientContract contract) {
        contracts.add(contract);
    }

    public HotelClientContract getContractOfClient(HotelClient client) {
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
