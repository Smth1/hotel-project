package com.roma.distr.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class RoomDTO {
    private int number;
    private int clientNumber;
}
