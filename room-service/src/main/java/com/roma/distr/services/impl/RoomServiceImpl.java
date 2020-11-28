package com.roma.distr.services.impl;

import com.roma.distr.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import com.roma.distr.repository.RoomRepository;
import com.roma.distr.services.RoomService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

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
    public void setClean(UUID roomId) {
        Optional<Room> optionalRoom = this.roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setClean();
            this.roomRepository.saveAndFlush(room);
        }
    }

    @Override
    public String toString() {
        return "Hotel rooms{" +
                "rooms=" + roomRepository.findAll() +
                '}';
    }
}
