package bg.tu_varna.sit.f24621726.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Event {
    private String name;
    private Date date;
    private Hall hall;
    private ArrayList<Ticket> tickets;
    //масив с информация за местата
    private Seat [] [] seats;

    public Seat[][] getSeats() {
        return seats;
    }

    public Event(String name, Date date, Hall hall ) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        tickets = new ArrayList<Ticket>();

        //създавана на матрица с места при инициализация на обекта Event
        seats = new Seat[hall.getNumberOfRows() + 1][hall.getSeatsPerRow() + 1];

        for (int i = 1; i <= hall.getNumberOfRows(); i++) {
            for (int j = 1; j <= hall.getSeatsPerRow(); j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Hall getHall() {
        return hall;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    public void addTicket(Ticket ticket){
        tickets.add(ticket);

    }
    public void removeTicket(Ticket ticket)
    {
        tickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", hall=" + hall +
                ", tickets=" + tickets +
                ", seats=" + seats.toString() +
                '}';
    }
}
