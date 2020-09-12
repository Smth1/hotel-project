package app;

import entities.*;
import services.*;

import java.util.List;

public final class Application {

    private static final String delimiter = "....................------------------------......................";

    public static void main(String... args) {
        System.out.println("Hotel started its work\n" + delimiter);

        System.out.println("\nCreating hotel rooms \n" + delimiter);
        Room room1 = new Room(1,2);
        Room room2 = new Room(2,4);
        Room room3 = new Room(3,2);
        Room room4 = new Room(4,3);

        System.out.println("\nCreating hotel staff \n" +  delimiter);
        Employee admin = new Administrator("George", 34, "+380-635-5561-48");
        Employee porter = new Porter("Jack", 25);
        Employee maid = new Maid("Amelia", 39);
        Employee cashier = new Cashier("Ella", 30);

        System.out.println("\nCreating hotel services \n" + delimiter);
        AdminService adminService = new AdminService((Administrator)admin);
        RoomService roomService = new RoomService();
        ClientService clientService = new ClientService();
        HousekeepingService housekeepingService = new HousekeepingService(roomService, adminService);
        ReceptionService receptionService =
                new ReceptionService(adminService,clientService,roomService);

        System.out.println("\nRooms are added to the service\n" + delimiter);
        roomService.addRoom(room1);
        roomService.addRoom(room2);
        roomService.addRoom(room3);
        roomService.addRoom(room4);

        System.out.println("\nEmployers are added to the service \n" + delimiter);
        adminService.addEmployer(admin);
        adminService.addEmployer(porter);
        adminService.addEmployer(maid);
        adminService.addEmployer(cashier);

        System.out.println("\nRooms must be clean before settling");
        housekeepingService.cleanRooms();


        HotelClient client1 = new HotelClient("Asher");
        HotelClient client2 = new HotelClient("Oliver");
        HotelClient client3 = new HotelClient("Silas");
        HotelClient client4 = new HotelClient("Atticus");

        System.out.println("\nClients are at the reception\n" + delimiter);
        receptionService.serveClient(client1);
        receptionService.serveClient(client2);
        receptionService.serveClient(client3);
        receptionService.serveClient(client4);

        System.out.println("\nCustomer contracts \n" + delimiter);
        List<HotelClientContract> contracts = clientService.getContracts();
        for (HotelClientContract contract : contracts) {
            System.out.println(contract);
        }

        System.out.println("\nСlients have lived and moving out \n" + delimiter);
        receptionService.moveOutClient(client2);
        receptionService.moveOutClient(client1);
        receptionService.moveOutClient(client3);
        receptionService.moveOutClient(client4);

        System.out.println("\nRooms must be clean after moving out");
        housekeepingService.cleanRooms();

        System.out.println("\nAll cleaning reports for this period \n" + delimiter);
        List<CleaningReport> reports = housekeepingService.getCleaningReports();
        for (CleaningReport report : reports) {
            System.out.println(report);
        }

        System.out.println("\nClosed contracts for this period \n" + delimiter);
        List<HotelClientContract> closedContracts = clientService.getContracts();
        for (HotelClientContract contract : closedContracts) {
            System.out.println(contract);
        }
    }
}
