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
    @OneToMany(cascade = { CascadeType.ALL })
    private List<HotelClient> homeClients;

    public Room() {
    }

    public Room(int number, int clientNumber) {
        Random random = new Random();

        homeClients = new ArrayList<>();
        this.number = number;
        this.clientNumber = clientNumber;
        this.isFree = true;
        isClean = random.nextBoolean();
    }

    public void addClient(HotelClient client) {
        homeClients.add(client);
        if (homeClients.size() == this.clientNumber)
            this.isFree = false;
    }

    public void removeClient(HotelClient client) {
        homeClients.remove(client);
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
