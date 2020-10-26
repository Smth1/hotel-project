package com.roma.distr.entities.dto;

import com.roma.distr.entities.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomsDTO {
    private List<Room> rooms;
}
