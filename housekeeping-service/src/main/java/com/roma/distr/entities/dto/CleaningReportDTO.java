package com.roma.distr.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CleaningReportDTO {
    private UUID id;

    private  UUID administratorId;

    private  UUID maidId;

    private  String rooms;

    private LocalDateTime creationDate;
}
