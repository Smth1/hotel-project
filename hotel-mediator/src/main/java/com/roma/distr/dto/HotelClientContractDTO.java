package com.roma.distr.dto;

import lombok.Data;

import java.util.UUID;

@Data
public final class HotelClientContractDTO {
    private UUID id;

    private UUID hotelAdmin;

    private UUID client;

    private UUID cashier;

    private UUID porter;

    private UUID room;

    private String creationDate;
    private String closingDate;
    private boolean isOpen;
}
