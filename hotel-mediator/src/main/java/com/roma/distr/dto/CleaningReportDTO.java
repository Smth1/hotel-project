package com.roma.distr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CleaningReportDTO {
    private UUID id;

    private  UUID administratorId;

    private  UUID maidId;

    private  String rooms;

    private  String creationDate;

    public CleaningReportDTO() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getAdministratorId() {
        return administratorId;
    }

    public UUID getMaidId() {
        return maidId;
    }

    public String getRooms() {
        return rooms;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAdministratorId(UUID administratorId) {
        this.administratorId = administratorId;
    }

    public void setMaidId(UUID maidId) {
        this.maidId = maidId;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
