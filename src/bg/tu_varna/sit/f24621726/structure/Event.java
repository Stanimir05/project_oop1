package bg.tu_varna.sit.f24621726.structure;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private String name;
    private Date date;
    private int hallNumber;
    private Map<String, Ticket> tickets;
    private Seat[][] seats;

    public Event(String name, Date date, Hall hall) {
        this.name = name;
        this.date = date;
        this.hallNumber = hall.getNumber();
        this.tickets = new HashMap<String, Ticket>();

        seats = new Seat[hall.getNumberOfRows() + 1][hall.getSeatsPerRow() + 1];

        for (int i = 0; i <= hall.getNumberOfRows(); i++) {
            for (int j = 0; j <= hall.getSeatsPerRow(); j++) {
                seats[i][j] = new Seat(j, i);
            }
        }
    }
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event other = (Event) obj;

        return hallNumber == other.hallNumber
                && name.equals(other.name)
                && date.equals(other.date);
    }
    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getCode(), ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket.getCode());
    }
    public int getTotalSeats() {

        return (seats.length - 1) *
                (seats[0].length - 1);
    }

    @Override
    public String toString() {
        return "Event: " + name +
                "\nDate: " + date +
                "\nHall: " + hallNumber +
                "\nTickets sold: " + tickets.size();
    }
}