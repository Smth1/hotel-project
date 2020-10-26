package com.roma.distr.entities;

import javax.persistence.*;
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
    public Room() {
    }

    public Room(int number, int clientNumber) {
        Random random = new Random();
        this.number = number;
        this.clientNumber = clientNumber;
        this.isFree = true;
        isClean = random.nextBoolean();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
