package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.exceptions.*;
import bg.tu_varna.sit.f24621726.enums.SeatStatus;

import java.util.*;
/**
 * Основен клас, съдържащ бизнес логиката
 * на системата за управление на билети.
 *
 * Класът управлява:
 * - зали
 * - събития
 * - места
 * - билети
 * - резервации
 *
 * Предоставя функционалности за:
 * - добавяне и премахване на зали и събития
 * - резервиране и закупуване на билети
 * - търсене на събития
 * - генериране на справки
 * - проверка на билети
 */
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
    /**
     * Добавя зала в системата.
     *
     * @param hall залата за добавяне
     */
    public void addHall(Hall hall)
    {
        halls.put(hall.getNumber(),hall);
    }
    /**
     * Премахва зала от системата.
     *
     * @param hall залата за премахване
     */
    public void removeHall(Hall hall)
    {
        halls.remove(hall.getNumber());
    }
    /**
     * Намира зала по подаден номер.
     *
     * @param hallNumber номер на залата
     * @return намерената зала
     * @throws NotFoundException ако залата не съществува
     */
    public Hall findHall(int hallNumber) throws NotFoundException {
        Hall hall = halls.get(hallNumber);
        if (hall == null) {
            throw new NotFoundException("Hall with number " + hallNumber + " not found!");
        }
        return hall;
    }
    /**
     * Добавя ново събитие в системата.
     *
     * @param date дата на събитието
     * @param hallNumber номер на залата
     * @param name име на събитието
     * @throws Exception ако залата не съществува
     *                   или събитието вече е добавено
     */
    public void addEvent(Date date, int hallNumber, String name) throws Exception {
        Hall hall = findHall(hallNumber);

        for (Event event : events) {
            if (event.getHallNumber()== hallNumber
                    && event.getDate().equals(date)
                    && event.getName().equals(name)) {
                throw new InvalidArgumentsException(
                        "Hall " + hallNumber + " already has this event on " + date + "!"
                );
            }
        }

        Event newEvent = new Event(name, date, hall);
        events.add(newEvent);
    }
    /**
     * Изчиства всички данни от системата.
     *
     * Използва се основно при зареждане на файл.
     */
    public void clear(){
        events.clear();
        halls.clear();
    }
    /**
     * Зарежда примерни начални данни в системата.
     *
     * @throws Exception при невалидни данни
     */
    public void loadDefaultData() throws Exception {


        addHall(new Hall(1, 12, 14));
        addHall(new Hall(2, 10, 12));
        addHall(new Hall(3, 8, 10));
        addHall(new Hall(4, 15, 20));
        addHall(new Hall(5, 6, 8));


        Date date1 = java.sql.Date.valueOf("2026-06-10");
        Date date2 = java.sql.Date.valueOf("2026-06-12");
        Date date3 = java.sql.Date.valueOf("2026-06-15");
        Date date4 = java.sql.Date.valueOf("2026-06-18");
        Date date5 = java.sql.Date.valueOf("2026-06-20");
        Date date6 = java.sql.Date.valueOf("2026-06-22");


        addEvent(date1, 1, "Rock Festival");
        addEvent(date2, 2, "Jazz Night");
        addEvent(date3, 3, "Movie Premiere");
        addEvent(date4, 4, "Stand Up Comedy");
        addEvent(date5, 5, "Classical Concert");
        addEvent(date6, 1, "Tech Conference");


        book(2, 5, date1, "Rock Festival", "Reserved for sponsors");
        book(3, 7, date1, "Rock Festival", "VIP guest");

        book(1, 1, date2, "Jazz Night", "Front row reservation");
        book(2, 4, date2, "Jazz Night", "Reserved");

        book(4, 2, date3, "Movie Premiere", "Press seat");
        book(5, 5, date3, "Movie Premiere", "Special guest");

        book(6, 10, date4, "Stand Up Comedy", "Organizer");
        book(7, 12, date4, "Stand Up Comedy", "VIP");


        buy(1, 1, date1, "Rock Festival");
        buy(1, 2, date1, "Rock Festival");
        buy(1, 3, date1, "Rock Festival");
        buy(2, 1, date1, "Rock Festival");
        buy(2, 2, date1, "Rock Festival");
        buy(5, 10, date1, "Rock Festival");


        buy(3, 3, date2, "Jazz Night");
        buy(3, 4, date2, "Jazz Night");
        buy(4, 5, date2, "Jazz Night");


        buy(1, 1, date3, "Movie Premiere");
        buy(1, 2, date3, "Movie Premiere");
        buy(1, 3, date3, "Movie Premiere");
        buy(2, 1, date3, "Movie Premiere");
        buy(2, 2, date3, "Movie Premiere");
        buy(2, 3, date3, "Movie Premiere");
        buy(3, 1, date3, "Movie Premiere");
        buy(3, 2, date3, "Movie Premiere");


        buy(10, 10, date4, "Stand Up Comedy");
        buy(10, 11, date4, "Stand Up Comedy");


        buy(1, 1, date5, "Classical Concert");


        buy(1, 1, date6, "Tech Conference");
    }
    /**
     * Премахва събитие от системата.
     *
     * @param event събитието за премахване
     */
    public void removeEvent(Event event)
    {
        events.remove(event);
    }
    /**
     * Намира събитие по име и дата.
     *
     * @param name име на събитието
     * @param date дата на събитието
     * @return намереното събитие
     * @throws NotFoundException ако събитието не е намерено
     */
    public Event findEvent(String name, Date date) throws NotFoundException{
        for (Event e : events) {
            if (e.getName().equals(name) && e.getDate().equals(date)) {
                return e;
            }
        }
        throw new NotFoundException("Event not found!");
    }
    /**
     * Намира всички събития за дадена дата.
     *
     * @param date дата за търсене
     * @return списък със събития
     * @throws NotFoundException ако няма намерени събития
     */
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
/**
 * Намира всички събития с дадено име.
 *
 * @param name име на събитието
 * @return списък със събития
 * @t
 * */
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
    /**
     * Резервира място за събитие.
     *
     * @param row номер на ред
     * @param seat номер на място
     * @param date дата на събитието
     * @param eventName име на събитието
     * @param note бележка към резервацията
     * @throws Exception при невалидно събитие
     *                   или заето място
     */
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
    /**
     * Отменя резервация на място.
     *
     * @param row номер на ред
     * @param seat номер на място
     * @param date дата на събитието
     * @param eventName име на събитието
     * @throws Exception ако мястото не е резервирано
     */
    public void unbook(int row,int seat,Date date,String eventName)throws Exception {
        //открива събитието за отмяна на билета
        Event event=findEvent(eventName,date);

        Seat seatToUnbook=findSeat(event,row,seat);
        if(!seatToUnbook.isBooked())
        {
           throw new NotAvailableException("Seat is "+seatToUnbook.getStatus().toString().toLowerCase()+"!");
        }

        seatToUnbook.setStatus(SeatStatus.FREE);
        seatToUnbook.setNote(null);
    }
    /**
     * Закупува билет за място.
     *
     * @param row номер на ред
     * @param seat номер на място
     * @param date дата на събитието
     * @param eventName име на събитието
     * @return генерираният билет
     * @throws Exception ако мястото не може да бъде закупено
     */
    public Ticket buy(int row, int seat, Date date, String eventName) throws Exception {
        Event event = findEvent(eventName, date);
        Seat seatToBuy = findSeat(event, row, seat);
        if (seatToBuy.isBought()) {
            throw new NotAvailableException("Seat is already bought!");
        }
        seatToBuy.setStatus(SeatStatus.BOUGHT);
        seatToBuy.setNote(null);
        Ticket ticket = new Ticket(seatToBuy, event);
        event.getTickets().put(ticket.getCode(), ticket);
        return ticket;
    }
    /**
     * Връща всички свободни места за събитие.
     *
     * @param date дата на събитието
     * @param eventName име на събитието
     * @return списък със свободни места
     */
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
    /**
     * Връща всички резервирани места.
     *
     * @param date дата на събитието
     * @param eventName име на събитието
     * @return списък с резервирани места
     * @throws Exception при невалидни параметри
     */
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
    /**
     * Намира място в матрицата на събитие.
     *
     * @param event събитието
     * @param row номер на ред
     * @param seat номер на място
     * @return намереното място
     * @throws InvalidArgumentsException
     *         ако мястото е извън границите
     */
    public Seat findSeat(Event event, int row, int seat) throws InvalidArgumentsException {

        Seat[][] seats = event.getSeats();

        if (row < 0 || row >= seats.length ||
                seat < 0 || seat >= seats[row].length) {
            throw new InvalidArgumentsException("Seat position is out of bounds");
        }

        return seats[row][seat];
    }
    /**
     * Намира билет по неговия код.
     *
     * @param code код на билета
     * @return намерения билет
     * @throws InvalidArgumentsException
     *         ако билетът не съществува
     */
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
    /**
     * Проверява билет по код.
     *
     * @param code код на билета
     * @return мястото, за което е издаден билетът
     */
    public Seat checkTicket(String code) {
        Ticket ticket = findTicketByCode(code);
        return ticket.getSeat();
    }
    /**
     * Генерира справка за продадени билети
     * за даден период и зала.
     *
     * @param from начална дата
     * @param to крайна дата
     * @param hallNumber номер на зала
     * @return map със събития и брой продадени билети
     */
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
    /**
     * Генерира текстова информация за събития.
     *
     * @param events списък със събития
     * @return текстово представяне на събитията
     */
    public String displayEvents(List<Event> events){
        StringBuilder result= new StringBuilder();
        for(Event e:events)
        {
            result.append(e.toString()).append("\n\n");
        }
        return result.toString().trim();
}
    /**
     * Генерира текстова информация за залите.
     *
     * @return текстово представяне на залите
     */
    public String displayHalls(){
    StringBuilder result= new StringBuilder();
    for(Hall h:halls.values())
    {
        result.append(h.toString()).append("\n");
    }
    return result.toString().trim();
}
    /**
     * Намира всички събития за дадена зала.
     *
     * @param hallNumber номер на залата
     * @return списък със събития
     */
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
    /**
     * Връща топ 10 събития
     * според броя продадени билети.
     *
     * @return сортиран списък със събития
     */
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
    /**
     * Намира събития с продажби
     * под зададен процент.
     *
     * @param percent процент продажби
     * @return списък със събития
     */
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
    /**
     * Премахва списък от събития.
     *
     * @param eventsToRemove събития за премахване
     */
    public void removeEventsByList(List<Event> eventsToRemove) {

        for (Event eventToRemove : eventsToRemove) {
            events.remove(eventToRemove);
        }
    }
}
