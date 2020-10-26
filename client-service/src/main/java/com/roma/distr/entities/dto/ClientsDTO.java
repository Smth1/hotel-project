package com.roma.distr.entities.dto;

import com.roma.distr.entities.HotelClient;
import lombok.Data;

import java.util.List;

@Data
public final class ClientsDTO {
    private List<HotelClientDTO> clients;
}
