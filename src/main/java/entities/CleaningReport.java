package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public final class CleaningReport {
    private final Long id;
    private final Administrator administrator;
    private final Maid maid;
    private final List<Room> rooms;
    private final LocalDateTime creationDate;

    public CleaningReport(Administrator administrator, Maid maid, List<Room> rooms) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.administrator = administrator;
        this.maid = maid;
        this.rooms = rooms;
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
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
