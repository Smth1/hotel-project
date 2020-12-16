package com.roma.distr.services.grpc;

import com.roma.distr.entities.HotelClient;

import java.util.List;

public interface GrpcClientService {
    List<HotelClient> getClients();

    HotelClient getClientById(String id);

    void serveClient(HotelClient client);

    void moveOutClient(String clientId);
}
