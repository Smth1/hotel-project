package services.impl;

import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import services.ClientService;
import services.ReceptionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public  class ReceptionServiceImpl implements ReceptionService {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoomServiceImpl roomService;

    public void serveClient(HotelClient client) {
        List<Room> rooms = roomService.getAvailableRooms();
        Random random = new Random();
        Room room = rooms.get(Math.abs(random.nextInt(rooms.size())));

        this.concludeContract(client,room);
        clientService.addClient(client);
        roomService.settleClient(client,room);
    }

    public void concludeContract(HotelClient client, Room room) {
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
            throw new NoSuchElementException();

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
