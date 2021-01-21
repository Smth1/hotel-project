package com.roma.distr.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class HotelClientDTO {
    private UUID clientId;
    private String clientName;

    public HotelClientDTO(String clientName) {
        this.clientName = clientName;
    }
}
