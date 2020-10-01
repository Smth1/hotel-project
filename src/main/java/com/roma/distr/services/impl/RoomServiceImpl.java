package com.roma.distr.services.impl;

import com.roma.distr.entities.HotelClient;
import com.roma.distr.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import com.roma.distr.repository.RoomRepository;
import com.roma.distr.services.RoomService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        List<Room> allRooms = roomRepository.findAll();

        for (Room room : allRooms) {
            if (room.isFree()) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    @Override
    public List<Room> getUncleanRooms() {
        List<Room> uncleanRooms = new ArrayList<>();
        List<Room> allRooms = roomRepository.findAll();

        for(Room room : allRooms) {
            if (!room.isClean()) {
                uncleanRooms.add(room);
            }
        }

        return uncleanRooms;
    }

    @Override
    public void settleClient(HotelClient client, Room room) {
        room.addClient(client);
        roomRepository.saveAndFlush(room);
    }

    @Override
    public void moveOutClient(HotelClient client) {
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            room.removeClient(client);
        }
    }

    @Override
    public String toString() {
        return "Hotel rooms{" +
                "rooms=" + roomRepository.findAll() +
                '}';
    }
}
