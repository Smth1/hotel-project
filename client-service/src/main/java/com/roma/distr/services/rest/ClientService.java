package com.roma.distr.services.rest;

import com.roma.distr.entities.HotelClient;

import java.util.List;

public interface ClientService {
    List<HotelClient> getClients();

    HotelClient getClientById(String id);

    void serveClient(HotelClient client);

    void moveOutClient(String clientId);
}
