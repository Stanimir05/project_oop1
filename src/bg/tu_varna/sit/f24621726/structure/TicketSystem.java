package bg.tu_varna.sit.f24621726.structure;

import java.util.ArrayList;
import java.util.Date;

public class TicketSystem {
    private ArrayList<Hall> halls;
    private  ArrayList<Event> events;

    public TicketSystem() {
       halls=new ArrayList<Hall>();
       events=new ArrayList<Event>();
    }
    public void addHall(Hall hall)
    {
        halls.add(hall);
    }
    public void removeHall(Hall hall)
    {
        halls.remove(hall);
    }

    public void addEvent(Event event)
    {
        events.add(event);
    }
    public void removeEvent(Event event)
    {
        events.remove(event);
    }
    //търсене на конкретно събитие по подадени име и дата
    Event findEvent(String name, Date date) {
        for (Event e : events) {
            if (e.getName().equals(name) && e.getDate().equals(date)) {
                return e;
            }
        }
        return null;
    }
//запазване (без закупуване) на билет
    public void book(int row,int seat,Date date,String eventName,String note){
       //търсене на събитието по подадаените дата и име
        Event event=findEvent(eventName,date);
        //роверява дали събитието не е открито
        if (event==null)
    {
        //функцията се прекратява при неоткрито събитие
    System.out.println("Event not found");
    return;
    }
        //окрива мястото в масива с мества за съответното събитие
        Seat seatToBook = event.getSeats()[row][seat];
        //проверка дали мястото е свободно

        if (seatToBook.getStatus() != SeatStatus.FREE) {
            //при несвободно място функцията се прекратява
            System.out.println("Seat isn`t available");
            return;
        }
        seatToBook.setStatus(SeatStatus.BOOKED);
        seatToBook.setNote(note);

        System.out.println("Seat is booked");
    }
//отмяна на запазването на билет
    public void unbook(int row,int seat,Date date,String eventName)
    {
        //открива събитието за отмяна на билета
        Event event=findEvent(eventName,date);
        if(event==null)
        {
            System.out.println("Event not found");
            return;
        }
        Seat seatToUnbook=event.getSeats()[row][seat];
        if(seatToUnbook.getStatus()==SeatStatus.FREE)
        {
            System.out.println("Seat is not booked and is free");
            return;
        }
        else if(seatToUnbook.getStatus()==SeatStatus.BOUGHT){
            System.out.println("Seat is already paid for");
            return;
        }
        seatToUnbook.setStatus(SeatStatus.FREE);
        seatToUnbook.setNote(null);
        System.out.println("Seat is unbooked");
    }
}
