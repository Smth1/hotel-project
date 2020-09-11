package services;

import entities.HotelClient;
import entities.Room;

import java.util.List;
import java.util.Random;

public final class ReceptionService {
    private final ClientService clientService;
    private final RoomService roomService;

    public ReceptionService(ClientService clientService, RoomService roomService) {
        this.clientService = clientService;
        this.roomService = roomService;

        System.out.println("Created reception service");
    }

    public void serveClient(HotelClient client) {
        System.out.println("\nSettlement of the client: " + client
                + "---------------------------------");

        List<Room> rooms = roomService.getAvailableRooms();
        Random random = new Random();

        clientService.addClient(client);

        Room room = rooms.get(Math.abs(random.nextInt())%rooms.size());
        roomService.settleClient(client,room);

        System.out.println("\t chose a room: " + room + " and settled in it");
    }

    @Override
    public String toString() {
        return "ReceptionService{" +
                "clientService=" + clientService +
                ", roomService=" + roomService +
                '}';
    }
}
