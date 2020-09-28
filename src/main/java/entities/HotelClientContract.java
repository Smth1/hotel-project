package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "hotel_client_contract")
public class HotelClientContract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="admin_id", nullable=false)
    private Administrator hotelAdmin;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private HotelClient client;

    @ManyToOne
    @JoinColumn(name="cashier_id", nullable=false)
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name="porter_id", nullable=false)
    private Porter porter;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;
    private boolean isOpen;

    public HotelClientContract(Administrator hotelAdmin,
                        HotelClient client,
                        Room room,
                        Cashier cashier,
                        Porter porter) {
        DateTimeFormatter dataTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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
        builder.append(" contract id: ").append(id).append("\n");
        builder.append(" hotelAdmin: ").append(this.getHotelAdmin().getName()).append(" ");
        builder.append(" telephoneNumber: " + this.getHotelAdmin().getTelephoneNumber() + "\n");
        builder.append(" room number: ").append(this.getRoom().getNumber()).append("\n");
        builder.append(" client: " + client + "\n");
        builder.append(" cashier at reception: " + this.getCashier().getName() + "\n");
        builder.append(" attendant porter: ").append(this.getPorter().getName()).append("\n");
        builder.append(" creationDate: ").append(this.getCreationDate());
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
