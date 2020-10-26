package com.roma.distr.entities;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cleaning_report")
@AllArgsConstructor
public class CleaningReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private  UUID administratorId;

    private  UUID maidId;

    private  String rooms;

    private  LocalDateTime creationDate;

    public CleaningReport() {
    }

    public CleaningReport(UUID administrator, UUID maid, String rooms) {
        this.id = UUID.randomUUID();
        this.administratorId = administrator;
        this.maidId = maid;
        this.rooms = rooms;
        this.creationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(UUID administratorId) {
        this.administratorId = administratorId;
    }

    public UUID getMaidId() {
        return maidId;
    }

    public void setMaidId(UUID maidId) {
        this.maidId = maidId;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append("               CleaningReport          " + "\n" +
                "   id: " + this.getId() + "\n" +
                "   administrator: " + this.getAdministratorId() + "\n" +
                "   maid: " + this.getMaidId() + "\n");
        reportBuilder.append("   room numbers: ");
        reportBuilder.append("[" + rooms+"]");
        reportBuilder.append("\n");
        reportBuilder.append("   creationDate: " + this.getCreationDate());
        return reportBuilder.toString();
    }
}
