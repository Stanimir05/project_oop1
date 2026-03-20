package bg.tu_varna.sit.f24621726.structure;

import java.util.Date;

public class Application {
    static void main(String[] args) {

        TicketSystem ticketSystem = new TicketSystem();
        Hall hall1 = new Hall(10, 6, 1);
        Hall hall2 = new Hall(15, 10, 2);
        Hall hall3 = new Hall(18, 10, 3);

        ticketSystem.addHall(hall1);
        ticketSystem.addHall(hall2);
        ticketSystem.addHall(hall3);

        Event event1 = new Event("Phantom of the opera", new Date(2022, 12, 3), hall1);
        Event event2 = new Event("The Odesy", new Date(2022, 11, 26), hall2);

        ticketSystem.addEvent(event1);
        ticketSystem.addEvent(event2);

//kupuvane na bilet
    }
}
