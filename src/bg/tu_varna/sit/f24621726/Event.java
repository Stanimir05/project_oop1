package bg.tu_varna.sit.f24621726;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Event {
    private String name;
    private String date;
    private Hall hall;
    private ArrayList<Ticket> tickets;

    public Event(String name, String date, Hall hall, ArrayList<Ticket> tickets) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
