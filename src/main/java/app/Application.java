package app;

import entities.*;
import services.*;

public class Application {

    private static final String delimiter = "....................------------------------......................";

    public static void main(String... args) {


        System.out.println("\nCreating hotel rooms \n" + delimiter);
        Room room1 = new Room(1,2);
        Room room2 = new Room(2,4);
        Room room3 = new Room(3,2);
        Room room4 = new Room(4,3);

        System.out.println("\nCreating hotel staff \n" +  delimiter);
        Employer admin = new Administrator("George");
        Employer porter = new Porter("Jack");
        Employer maid = new Maid("Amelia");
        Employer cashier = new Cashier("Ella");

        System.out.println("\nCreating hotel services \n" + delimiter);
        AdminService adminService = new AdminService((Administrator)admin);
        RoomService roomService = new RoomService();
        ClientService clientService = new ClientService();
        HousekeepingService housekeepingService = new HousekeepingService(roomService, adminService);
        ReceptionService receptionService = new ReceptionService(clientService,roomService);

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

        System.out.println("\nClients are at the reception\n" + delimiter);
        HotelClient client1 = new HotelClient("Asher");
        HotelClient client2 = new HotelClient("Oliver");
        HotelClient client3 = new HotelClient("Silas");
        HotelClient client4 = new HotelClient("Atticus");

        receptionService.serveClient(client1);
        receptionService.serveClient(client2);
        receptionService.serveClient(client3);
        receptionService.serveClient(client4);
    }
}
