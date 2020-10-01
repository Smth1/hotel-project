package com.roma.distr.services;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.Room;

public interface ReceptionService {
    void serveClient(HotelClient client);
    void concludeContract(HotelClient client, Room room);
    void moveOutClient(String clientId);
    void closeContract(HotelClient client);
}
