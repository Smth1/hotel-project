package com.roma.distr.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cleaning_report")
public class CleaningReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="admin_id", nullable=false)
    private  Administrator administrator;

    @ManyToOne
    @JoinColumn(name="maid_id", nullable=false)
    private  Maid maid;

    @ManyToMany(cascade = { CascadeType.ALL })
    private  List<Room> rooms;
    private  LocalDateTime creationDate;

    public CleaningReport() {
    }

    public CleaningReport(Administrator administrator, Maid maid, List<Room> rooms) {
        this.id = UUID.randomUUID();
        this.administrator = administrator;
        this.maid = maid;
        this.rooms = rooms;
        this.creationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public Maid getMaid() {
        return maid;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getCreationDate() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTimeFormatter.format(creationDate);
    }


    @Override
    public String toString() {
        StringBuilder reportBuilder = new StringBuilder();
        StringBuilder roomNumbers = new StringBuilder();
        List<Room> rooms = getRooms();

        roomNumbers.append("[");
        for (Room room : rooms) {
            roomNumbers.append(String.valueOf(room.getNumber()) +
                    " ");
        }
        roomNumbers.append("]");

        reportBuilder.append("               CleaningReport          " + "\n" +
                "   id: " + this.getId() + "\n" +
                "   administrator: " + this.getAdministrator().getName() + "\n" +
                "   maid: " + this.getMaid().getName() + "\n");
        reportBuilder.append("   room numbers: ");
        reportBuilder.append(roomNumbers);
        reportBuilder.append("\n");
        reportBuilder.append("   creationDate: " + this.getCreationDate());
        return reportBuilder.toString();
    }
}
