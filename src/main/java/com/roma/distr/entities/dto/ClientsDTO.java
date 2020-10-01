package com.roma.distr.entities.dto;

import com.roma.distr.entities.HotelClient;

import java.util.List;

public class ClientsDTO {
    private List<HotelClient> clients;

    public ClientsDTO(List<HotelClient> clients) {
        this.clients = clients;
    }

    public List<HotelClient> getClients() {
        return clients;
    }

    public void setClients(List<HotelClient> clients) {
        this.clients = clients;
    }
}
