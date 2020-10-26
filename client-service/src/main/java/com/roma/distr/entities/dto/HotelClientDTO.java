package com.roma.distr.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public final class HotelClientDTO {
    private UUID clientId;
    private String clientName;

    public HotelClientDTO(String clientName) {
        this.clientName = clientName;
    }
}
