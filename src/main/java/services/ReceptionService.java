package services;

import entities.HotelClient;
import entities.Room;

public interface ReceptionService {
    void serveClient(HotelClient client);
    void concludeContract(HotelClient client, Room room);//not mapped by REST
    void moveOutClient(HotelClient client);
    void closeContract(HotelClient client);//not mapped by REST
}
