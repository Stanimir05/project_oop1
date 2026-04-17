package bg.tu_varna.sit.f24621726.structure;

import java.util.*;

public class Event {
    private String name;
    private Date date;
    private Hall hall;
    private Map<String, Ticket> tickets;
    //масив с информация за местата
    private Seat [] [] seats;

    public Seat[][] getSeats() {
        return seats;
    }

    public Event(String name, Date date, Hall hall ) {
        this.name = name;
        this.date = date;
        this.hall = hall;
        tickets = new HashMap<String,Ticket>();

        //създавана на матрица с места при инициализация на обекта Event на базата на подадената зала
        seats = new Seat[hall.getNumberOfRows() + 1][hall.getSeatsPerRow() + 1];

        for (int i = 0; i <=hall.getNumberOfRows(); i++) {
            for (int j = 0; j <= hall.getSeatsPerRow(); j++) {
                seats[i][j] = new Seat(j, i);
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

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket){
        tickets.put(ticket.getCode(),ticket);

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
