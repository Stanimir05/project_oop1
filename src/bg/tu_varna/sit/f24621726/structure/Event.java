package bg.tu_varna.sit.f24621726.structure;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Клас, описващ събитие в системата.
 *
 * Всяко събитие съдържа:
 * - име
 * - дата
 * - номер на зала
 * - списък с билети
 * - матрица с места
 *
 * Класът управлява информацията
 * за местата и билетите на събитието.
 */
public class Event {
    /**
     * Име на събитието.
     */
    private String name;
    /**
     * Дата на събитието.
     */
    private Date date;
    /**
     * Номер на залата,
     * в която се провежда събитието.
     */
    private int hallNumber;
    /**
     * Списък с билетите за събитието.
     *
     * Ключът е кодът на билета.
     */
    private Map<String, Ticket> tickets;
    /**
     * Матрица с местата за събитието.
     */
    private Seat[][] seats;
    /**
     * Създава ново събитие.
     *
     * Инициализира:
     * - информацията за събитието
     * - списъка с билети
     * - матрицата с места
     *
     * Всички места първоначално са свободни.
     *
     * @param name име на събитието
     * @param date дата на събитието
     * @param hall залата за събитието
     */
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
    /**
     * Изчислява общия брой места
     * в събитието.
     *
     * @return общ брой места
     */
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