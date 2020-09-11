package services;

import entities.HotelClient;

import java.util.ArrayList;
import java.util.List;

public final class ClientService {
    private final List<HotelClient> clients;

    public ClientService() {
        clients = new ArrayList<>();

        System.out.println("Created client service");
    }

    public void addClient(HotelClient client) {
        clients.add(client);

        System.out.println("Client" + client + " registered");
    }

    public List<HotelClient> getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "ClientService{" +
                "clients=" + this.getClients() +
                '}';
    }
}
