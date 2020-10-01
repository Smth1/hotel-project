package com.roma.distr.services.impl;

import com.roma.distr.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.roma.distr.services.ClientService;
import com.roma.distr.services.ReceptionService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public  class ReceptionServiceImpl implements ReceptionService {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoomServiceImpl roomService;

    @Override
    public void serveClient(HotelClient client) {
        List<Room> rooms = roomService.getAvailableRooms();
        Random random = new Random();
        System.out.println(rooms.size());
        int index = Math.abs(random.nextInt(rooms.size()));

        System.out.println(index);
        Room room = rooms.get(index);

        clientService.addClient(client);

        this.concludeContract(client,room);

        roomService.settleClient(client,room);
    }

    @Override
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

    @Override
    public void moveOutClient(String clientId) {
        HotelClient client = this.clientService.getClientById(clientId);
        if (client != null) {
            this.clientService.removeClient(client);
            this.roomService.moveOutClient(client);
        }
        else {
            System.out.println("Client id " + clientId + " not found.");
        }
    }

    @Override
    public void closeContract(HotelClient client) {
        HotelClientContract contract =
                clientService.getContractOfClient(client);
        if (contract != null) {
            contract.setClosedContract();
            clientService.removeContract(contract);
        }
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
