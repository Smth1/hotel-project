package services;

import entities.*;

import java.util.List;
import java.util.Random;

public final class ReceptionService {
    private final AdminService adminService;
    private final ClientService clientService;
    private final RoomService roomService;

    public ReceptionService(AdminService adminService,
                            ClientService clientService,
                            RoomService roomService) {
        this.clientService = clientService;
        this.roomService = roomService;
        this.adminService = adminService;

        System.out.println("Created reception service");
    }

    public void serveClient(HotelClient client) {
        List<Room> rooms = roomService.getAvailableRooms();
        Random random = new Random();
        Room room = rooms.get(Math.abs(random.nextInt(rooms.size())));

        this.concludeContract(client,room);
        clientService.addClient(client);
        roomService.settleClient(client,room);
    }

    private void concludeContract(HotelClient client, Room room) {
        Administrator admin = adminService.getAdmin();
        Cashier cashier = adminService.getRandomCashier();
        Porter porter = adminService.getRandomPorter();

        HotelClientContract contract =
                new HotelClientContract.ContractBuilder().
                        addAdministrator(admin).
                        addClient(client).
                        addRoom(room).
                        addCashier(cashier).
                        addPorter(porter).
                        createContract();
        clientService.addContract(contract);
    }

    public void moveOutClient(HotelClient client) {
        System.out.println("\nMoving of the client: " + client.getName());
        this.closeContract(client);
        this.clientService.removeClient(client);
        this.roomService.moveOutClient(client);

    }

    public void closeContract(HotelClient client) {
        HotelClientContract contract =
                clientService.getContractOfClient(client);
        if (contract != null)
            contract.setClosedContract();
        else
            clientService.addClientToBlackList(client);

    }

    @Override
    public String toString() {
        return "ReceptionService{" +
                "adminService=" + adminService +
                ", clientService=" + clientService +
                ", roomService=" + roomService +
                '}';
    }
}
