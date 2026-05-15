package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.exceptions.*;
import bg.tu_varna.sit.f24621726.enums.SeatStatus;
import java.util.Comparator;
import java.util.*;

public class TicketSystem {
    private Map<Integer, Hall> halls;
    private List<Event> events;

    public Map<Integer, Hall> getHalls() {
        return halls;
    }

    public List<Event> getEvents() {
        return events;
    }

    public TicketSystem() {
        halls = new HashMap<Integer, Hall>();
        events = new ArrayList<Event>();
    }
    public void addHall(Hall hall)
    {
        halls.put(hall.getNumber(),hall);
    }

    public void removeHall(Hall hall)
    {
        halls.remove(hall.getNumber());
    }
    public Hall findHall(int hallNumber) throws NotFoundException {
        Hall hall = halls.get(hallNumber);
        if (hall == null) {
            throw new NotFoundException("Hall with number " + hallNumber + " not found!");
        }
        return hall;
    }
    public void addEvent(Date date, int hallNumber, String name) throws Exception {
        Hall hall = findHall(hallNumber);

        for (Event event : events) {
            if (event.getHallNumber()== hallNumber
                    && event.getDate().equals(date)
                    && event.getName().equals(name)) {
                throw new InvalidArgumentsException(
                        "Hall " + hallNumber + " already has an event on " + date + "!"
                );
            }
        }

        Event newEvent = new Event(name, date, hall);
        events.add(newEvent);
    }
    public void clear(){
        events.clear();
        halls.clear();
    }
    public void loadDefaultData() throws Exception {

        // HALLS
        addHall(new Hall(1, 12, 14));
        addHall(new Hall(2, 10, 12));
        addHall(new Hall(3, 8, 10));
        addHall(new Hall(4, 15, 20));
        addHall(new Hall(5, 6, 8));

        // DATES
        Date date1 = java.sql.Date.valueOf("2026-06-10");
        Date date2 = java.sql.Date.valueOf("2026-06-12");
        Date date3 = java.sql.Date.valueOf("2026-06-15");
        Date date4 = java.sql.Date.valueOf("2026-06-18");
        Date date5 = java.sql.Date.valueOf("2026-06-20");
        Date date6 = java.sql.Date.valueOf("2026-06-22");

        // EVENTS
        addEvent(date1, 1, "Rock Festival");
        addEvent(date2, 2, "Jazz Night");
        addEvent(date3, 3, "Movie Premiere");
        addEvent(date4, 4, "Stand Up Comedy");
        addEvent(date5, 5, "Classical Concert");
        addEvent(date6, 1, "Tech Conference");

        // BOOKINGS
        book(2, 5, date1, "Rock Festival", "Reserved for sponsors");
        book(3, 7, date1, "Rock Festival", "VIP guest");

        book(1, 1, date2, "Jazz Night", "Front row reservation");
        book(2, 4, date2, "Jazz Night", "Reserved");

        book(4, 2, date3, "Movie Premiere", "Press seat");
        book(5, 5, date3, "Movie Premiere", "Special guest");

        book(6, 10, date4, "Stand Up Comedy", "Organizer");
        book(7, 12, date4, "Stand Up Comedy", "VIP");

        // PURCHASED TICKETS - ROCK FESTIVAL
        buy(1, 1, date1, "Rock Festival");
        buy(1, 2, date1, "Rock Festival");
        buy(1, 3, date1, "Rock Festival");
        buy(2, 1, date1, "Rock Festival");
        buy(2, 2, date1, "Rock Festival");
        buy(5, 10, date1, "Rock Festival");

        // PURCHASED TICKETS - JAZZ NIGHT
        buy(3, 3, date2, "Jazz Night");
        buy(3, 4, date2, "Jazz Night");
        buy(4, 5, date2, "Jazz Night");

        // PURCHASED TICKETS - MOVIE PREMIERE
        buy(1, 1, date3, "Movie Premiere");
        buy(1, 2, date3, "Movie Premiere");
        buy(1, 3, date3, "Movie Premiere");
        buy(2, 1, date3, "Movie Premiere");
        buy(2, 2, date3, "Movie Premiere");
        buy(2, 3, date3, "Movie Premiere");
        buy(3, 1, date3, "Movie Premiere");
        buy(3, 2, date3, "Movie Premiere");

        // PURCHASED TICKETS - STAND UP COMEDY
        buy(10, 10, date4, "Stand Up Comedy");
        buy(10, 11, date4, "Stand Up Comedy");

        // PURCHASED TICKETS - CLASSICAL CONCERT
        buy(1, 1, date5, "Classical Concert");

        // TECH CONFERENCE intentionally undersold
        buy(1, 1, date6, "Tech Conference");
    }
    public void removeEvent(Event event)
    {
        events.remove(event);
    }
    //търсене на конкретно събитие по подадени име и дата, ако събитието не е открито връща exception
    public Event findEvent(String name, Date date) throws NotFoundException{
        for (Event e : events) {
            if (e.getName().equals(name) && e.getDate().equals(date)) {
                return e;
            }
        }
        throw new NotFoundException("Event not found!");
    }
    public List<Event> findEventsByDate(Date date) throws NotFoundException{
        List<Event> eventsByDate = new ArrayList<>();

        for (Event e : events) {
            if (e.getDate().equals(date)) {
                eventsByDate.add(e); // add all events matching the date
            }
        }

        if (eventsByDate.isEmpty()) {
            throw new NotFoundException("No events found on this date!");
        }

        return eventsByDate;

    }
    public List<Event> findEventsByName(String name)throws NotFoundException{
        List<Event> eventsByName = new ArrayList<>();

        for (Event e : events) {
            if (e.getName().equals(name)) {
                eventsByName.add(e); // add all events matching the date
            }
        }

        if (eventsByName.isEmpty()) {
            throw new NotFoundException("No events found with this name!");
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
            throw new InvalidArgumentsException("Seat is "+seatToBook.getStatus().toString().toLowerCase()+"!");
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
    public Ticket buy(int row, int seat, Date date, String eventName) throws Exception {
        Event event = findEvent(eventName, date);
        Seat seatToBuy = findSeat(event, row, seat);
        if (seatToBuy.isBought()) {
            throw new SeatNotAvailableException("Seat is already bought!");
        }
        seatToBuy.setStatus(SeatStatus.BOUGHT);
        seatToBuy.setNote(null);
        Ticket ticket = new Ticket(seatToBuy, event);
        event.getTickets().put(ticket.getCode(), ticket);
        return ticket;
    }
    public List<Seat> freeSeats(Date date, String eventName) {
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
    public Seat findSeat(Event event, int row, int seat) throws InvalidArgumentsException {

        Seat[][] seats = event.getSeats();

        if (row < 0 || row >= seats.length ||
                seat < 0 || seat >= seats[row].length) {
            throw new InvalidArgumentsException("Seat position is out of bounds");
        }

        return seats[row][seat];
    }
    public Ticket findTicketByCode(String code) throws InvalidArgumentsException {
        for (Event e : events) {
            if (e.getTickets() != null) {
                Ticket ticket = e.getTickets().get(code);

                if (ticket != null) {
                    return ticket;
                }
            }
        }

        throw new InvalidArgumentsException("Ticket with such code doesn't exist");
    }
    public Seat checkTicket(String code) {
        Ticket ticket = findTicketByCode(code);
        return ticket.getSeat();
    }
    public Map<Event, Integer> report(Date from, Date to, Integer hallNumber) {
        Map<Event, Integer> result = new LinkedHashMap<>();

        for (Event event : events) {
            Date eventDate = event.getDate();

            boolean inRange = !eventDate.before(from) && !eventDate.after(to);
            boolean hallMatches = (hallNumber == null ||
                    event.getHallNumber()== hallNumber);

            if (inRange && hallMatches) {
                int soldCount = 0;

                for (Ticket ticket : event.getTickets().values()) {
                    if (ticket.getSeat().isBought()) {
                        soldCount++;
                    }
                }

                result.put(event, soldCount);
            }
        }

        return result;
    }
    public String displayEvents(List<Event> events){
        StringBuilder result= new StringBuilder();
        for(Event e:events)
        {
            result.append(e.toString()).append("\n\n");
        }
        return result.toString().trim();
}
    public String displayHalls(){
    StringBuilder result= new StringBuilder();
    for(Hall h:halls.values())
    {
        result.append(h.toString()).append("\n");
    }
    return result.toString().trim();
}
    public List<Event> findEventsByHallNumber(int hallNumber){
        List<Event> result=new ArrayList<>();
        for(Event e:events)
        {
            if(e.getHallNumber()==hallNumber)
            {
                result.add(e);
            }
        }
        return result;
}
    public List<Event> topEvents() {

        List<Event> sortedEvents = new ArrayList<>(events);

        Collections.sort(sortedEvents, (e1, e2) -> Integer.compare(
                e2.getTickets().size(),
                e1.getTickets().size()
        ));

        if (sortedEvents.size() > 10) {
            return sortedEvents.subList(0, 10);
        }

        return sortedEvents;
    }
    public List<Event> eventsUnderPercent(int percent) {

        List<Event> result = new ArrayList<>();

        for (Event event : events) {

            int soldTickets = event.getTickets().size();

            int totalSeats = event.getTotalSeats();

            double percentage =
                    (soldTickets * 100.0) / totalSeats;

            if (percentage < percent) {
                result.add(event);
            }
        }

        return result;
    }
    public void removeEventsByList(List<Event> eventsToRemove) {

        for (Event eventToRemove : eventsToRemove) {
            events.remove(eventToRemove);
        }
    }
}
