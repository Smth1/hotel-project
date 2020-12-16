package com.roma.distr.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "hotel_client_contract")
public class HotelClientContract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID adminId;

    private UUID clientId;

    private UUID cashierId;

    private UUID porterId;

    private UUID roomId;

    private String creationDate;
    private String closingDate;
    private boolean isOpen;

    public HotelClientContract() {
    }

    public HotelClientContract(UUID hotelAdmin,
                               UUID client,
                               UUID room,
                               UUID cashier,
                               UUID porter) {
        DateTimeFormatter dataTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.id = UUID.randomUUID();
        this.adminId = hotelAdmin;
        this.clientId = client;
        this.roomId = room;
        this.cashierId = cashier;
        this.porterId = porter;
        this.creationDate = LocalDateTime.now().toString();
        isOpen = true;
    }

    public UUID getId() {
        return id;
    }

    public UUID getHotelAdmin() {
        return adminId;
    }

    public UUID getClient() {
        return clientId;
    }

    public UUID getRoom() {
        return roomId;
    }

    public UUID getCashier() {
        return cashierId;
    }

    public UUID getPorter() {
        return porterId;
    }

    public String getCreationDate() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return creationDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosedContract() {
        this.isOpen = false;
        this.closingDate = LocalDateTime.now().toString();
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("               ClientHotelContract                 \n");
        builder.append(" contract id: ").append(id).append("\n");
        builder.append(" hotelAdmin: ").append(this.getHotelAdmin()).append(" ");
        builder.append(" room number: ").append(this.getRoom()).append("\n");
        builder.append(" client: " + this.getClient() + "\n");
        builder.append(" cashier at reception: " + this.getCashier() + "\n");
        builder.append(" attendant porter: ").append(this.getPorter()).append("\n");
        builder.append(" creationDate: ").append(this.getCreationDate());
        if (!isOpen)
            builder.append(" " + " closingDate: " + this.getClosingDate());

        return builder.toString();
    }

    public static class ContractBuilder {
        private UUID nestedHotelAdminId;
        private UUID nestedClientId;
        private UUID nestedRoomId;
        private UUID nestedCashierId;
        private UUID nestedPorterId;

        public ContractBuilder addAdministrator(
                UUID admin) {
            this.nestedHotelAdminId = admin;
            return this;
        }

        public ContractBuilder addClient(UUID client) {
            this.nestedClientId = client;
            return this;
        }

        public ContractBuilder addRoom(UUID room) {
            this.nestedRoomId = room;
            return this;
        }

        public ContractBuilder addCashier(UUID cashier) {
            this.nestedCashierId = cashier;
            return this;
        }

        public ContractBuilder addPorter(UUID porter) {
            this.nestedPorterId = porter;
            return this;
        }

        public HotelClientContract createContract() {
            return new HotelClientContract(nestedHotelAdminId,
                    nestedClientId,
                    nestedRoomId,
                    nestedCashierId,
                    nestedPorterId);
        }
    }
}
