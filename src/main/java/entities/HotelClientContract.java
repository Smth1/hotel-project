package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class HotelClientContract {
    private final Long id;
    private final Administrator hotelAdmin;
    private final HotelClient client;
    private final Cashier cashier;
    private final Porter porter;
    private final Room room;
    private final LocalDateTime creationDate;
    private LocalDateTime closingDate;
    private boolean isOpen;

    HotelClientContract(Administrator hotelAdmin,
                        HotelClient client,
                        Room room,
                        Cashier cashier,
                        Porter porter) {
        DateTimeFormatter dataTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.hotelAdmin = hotelAdmin;
        this.client = client;
        this.room = room;
        this.cashier = cashier;
        this.porter = porter;
        this.creationDate = LocalDateTime.now();
        isOpen = true;
    }

    public Administrator getHotelAdmin() {
        return hotelAdmin;
    }

    public HotelClient getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public Porter getPorter() {
        return porter;
    }

    public String getCreationDate() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTimeFormatter.format(creationDate);
    }

    public String getClosingDate() {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTimeFormatter.format(closingDate);
    }

    public void setClosedContract() {
        this.isOpen = false;
        this.closingDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("               ClientHotelContract                 \n");
        builder.append(" contract id: " + id + "\n");
        builder.append(" hotelAdmin: " + this.getHotelAdmin().getName() + " ");
        builder.append(" telephoneNumber: " + this.getHotelAdmin().getTelephoneNumber() + "\n");
        builder.append(" room number: " + this.getRoom().getNumber() + "\n");
        builder.append(" client: " + client + "\n");
        builder.append(" cashier at reception: " + this.getCashier().getName() + "\n");
        builder.append(" attendant porter: " + this.getPorter().getName() + "\n");
        builder.append(" creationDate: " + this.getCreationDate());
        if (!isOpen)
            builder.append(" " + " closingDate: " + this.getClosingDate());

        return builder.toString();
    }

    public static class ContractBuilder {
        private Administrator nestedHotelAdmin;
        private HotelClient nestedClient;
        private Room nestedRoom;
        private Cashier nestedCashier;
        private Porter nestedPorter;

        public ContractBuilder addAdministrator(
                Administrator admin) {
            this.nestedHotelAdmin = admin;
            return this;
        }

        public ContractBuilder addClient(HotelClient client) {
            this.nestedClient = client;
            return this;
        }

        public ContractBuilder addRoom(Room room) {
            this.nestedRoom = room;
            return this;
        }

        public ContractBuilder addCashier(Cashier cashier) {
            this.nestedCashier = cashier;
            return this;
        }

        public ContractBuilder addPorter(Porter porter) {
            this.nestedPorter = porter;
            return this;
        }

        public HotelClientContract createContract() {
            return new HotelClientContract(nestedHotelAdmin,
                    nestedClient,
                    nestedRoom,
                    nestedCashier,
                    nestedPorter);
        }
    }
}
