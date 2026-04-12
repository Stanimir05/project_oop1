package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.structure.exceptions.*;
import bg.tu_varna.sit.f24621726.structure.enums.SeatStatus;

import java.util.*;

public class TicketSystem {
    private Map<Integer, Hall> halls;
    private List<Event> events;
    private Map<String, Ticket> tickets;

    public TicketSystem() {
        halls = new HashMap<Integer, Hall>();
        events = new ArrayList<Event>();
        tickets = new HashMap<String, Ticket>();
    }
    public void addHall(Hall hall)
    {
        halls.put(hall.getNumber(),hall);
    }
    public void removeHall(Hall hall)
    {
        halls.remove(hall.getNumber());
    }
    public Hall findHall(int hallNumber) throws HallNotFoundException {
        Hall hall = halls.get(hallNumber);

        if (hall == null) {
            throw new HallNotFoundException("Hall with number " + hallNumber + " not found!");
        }

        return hall;
    }
    public void addEvent(Date date, int hallNumber, String name) throws Exception {
        Hall hall = findHall(hallNumber);

        for (Event event : events) {
            if (event.getHall().getNumber() == hallNumber && event.getDate().equals(date)) {
                throw new InvalidArgumentsException(
                        "Hall " + hallNumber + " already has an event on " + date + "!"
                );
            }
        }

        Event newEvent = new Event(name, date, hall);
        events.add(newEvent);
    }
    public void removeEvent(Event event)
    {
        events.remove(event);
    }
    //търсене на конкретно събитие по подадени име и дата, ако събитието не е открито връща exception
    public Event findEvent(String name, Date date) throws EventNotFoundException{
        for (Event e : events) {
            if (e.getName().equals(name) && e.getDate().equals(date)) {
                return e;
            }
        }
        throw new EventNotFoundException("Event not found!");
    }
    public List<Event> findEventsByDate(Date date) throws EventNotFoundException{
        List<Event> eventsByDate = new ArrayList<>();

        for (Event e : events) {
            if (e.getDate().equals(date)) {
                eventsByDate.add(e); // add all events matching the date
            }
        }

        if (eventsByDate.isEmpty()) {
            throw new EventNotFoundException("No events found on this date!");
        }

        return eventsByDate;

    }
    public List<Event> findEventsByName(String name)throws EventNotFoundException{
        List<Event> eventsByName = new ArrayList<>();

        for (Event e : events) {
            if (e.getName().equals(name)) {
                eventsByName.add(e); // add all events matching the date
            }
        }

        if (eventsByName.isEmpty()) {
            throw new EventNotFoundException("No events found with this name!");
        }

        return eventsByName;
    }
//запазване (без закупуване) на билет
    public void book(int row,int seat,Date date,String eventName,String note) throws Exception{
       //търсене на събитието по подадаените дата и име
        Event event=findEvent(eventName,date);
        //окрива мястото в масива с мества за съответното събитие
        Seat seatToBook = findSeat(event, row, seat);

        //проверка дали мястото е свободно
        if (!seatToBook.isFree()) {
            //при несвободно място функцията се прекратява
            throw new InvalidSeatException("Seat is "+seatToBook.getStatus().toString().toLowerCase()+"!");
        }
        seatToBook.setStatus(SeatStatus.BOOKED);
        seatToBook.setNote(note);
    }
//отмяна на запазването на билет
    public void unbook(int row,int seat,Date date,String eventName)throws Exception {
        //открива събитието за отмяна на билета
        Event event=findEvent(eventName,date);

        Seat seatToUnbook=findSeat(event,row,seat);
        if(!seatToUnbook.isBooked())
        {
           throw new SeatNotAvailableException("Seat is "+seatToUnbook.getStatus().toString().toLowerCase()+"!");
        }

        seatToUnbook.setStatus(SeatStatus.FREE);
        seatToUnbook.setNote(null);
    }
    public void buy(int row,int seat,Date date,String eventName)throws Exception {
        //открива събитието за закупуване на билета
        Event event=findEvent(eventName,date);

        Seat seatToBuy=findSeat(event,row,seat);
        if(seatToBuy.isBought())
        {
            throw new SeatNotAvailableException("Seat is already bought!");
        }

        seatToBuy.setStatus(SeatStatus.BOUGHT);

    }
    public List<Seat> freeSeats(Date date, String eventName) throws EventNotFoundException {
        Event event = findEvent(eventName, date);
        Seat[][] seats = event.getSeats();
        List<Seat> freeSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isFree()) {
                    freeSeats.add(seats[i][j]);
                }
            }
        }

        return freeSeats;
    }
    public List<Seat> bookings(Date date, String eventName)throws Exception{

        List<Seat> bookings = new ArrayList<>();
        List<Event> events=new ArrayList<>();

        if(eventName==null && date!=null )//неподадено име
        {
            events = findEventsByDate(date);
        }
        else if (date==null && eventName!=null) //неподадена дата
        {
            events = findEventsByName(eventName);
        }
        else if(date==null && eventName==null)// двата параметъра не са подадени
        {
            throw new InvalidArgumentsException("Both parameters cant be missed!");

        }
        else{
            events.add(findEvent(eventName, date));
        }
        for (Event e : events) {
            //матрицата се дефинира с всяка итерация,
            // защото всяко събитие има различна такава
            Seat[][] seats = e.getSeats();
            //преминаване през матрицата и записване на запазените места в bookings
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j].isBooked()) {
                        bookings.add(seats[i][j]);
                    }
                }
            }
            }

        return bookings;
    }
    //намира съответното място, при място извън обсега връща грешка
    //private, защото ще се използва само в този клас
    private Seat findSeat(Event event, int row, int seat) throws InvalidSeatException {

        Seat[][] seats = event.getSeats();

        if (row < 0 || row >= seats.length ||
                seat < 0 || seat >= seats[row].length) {
            throw new InvalidSeatException("Seat position is out of bounds");
        }

        return seats[row][seat];
    }
    private Ticket findTicketByCode(String code) throws InvalidCodeException {

        for (Ticket t:tickets)
        {
            if(t.getCode()==code){
                return t;
            }
        }
        throw new InvalidCodeException("Ticket with code "+code+" doesn`t exist");
}
    public Seat checkTicket(String code) {
        Ticket ticket = findTicketByCode(code);
        return ticket.getSeat();
    }

}
