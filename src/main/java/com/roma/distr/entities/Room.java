package com.roma.distr.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int number;
    private int clientNumber;
    private boolean isClean;
    private boolean isFree;

    @OneToMany(mappedBy = "room")
    private List<HotelClient> hotelClients;

    @ManyToMany(mappedBy = "rooms")
    private List<CleaningReport> reports;

    @OneToMany(mappedBy = "room")
    private List<HotelClientContract> contracts;

    public Room() {
    }

    public Room(int number, int clientNumber) {
        Random random = new Random();

        hotelClients = new ArrayList<>();
        this.number = number;
        this.clientNumber = clientNumber;
        this.isFree = true;
        isClean = random.nextBoolean();
    }

    public void addClient(HotelClient client) {
        hotelClients.add(client);
        if (hotelClients.size() == this.clientNumber)
            this.isFree = false;
    }

    public void removeClient(HotelClient client) {
        hotelClients.remove(client);
        this.isClean = false;
    }

    public int getNumber() {
        return number;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public boolean isClean() {
        return isClean;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setClean() {
        isClean = true;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + this.getNumber() +
                ", clientNumber=" + this.getClientNumber() +
                ", isClean=" + this.isClean() +
                ", isFree=" + this.isFree() +
                '}';
    }
}
