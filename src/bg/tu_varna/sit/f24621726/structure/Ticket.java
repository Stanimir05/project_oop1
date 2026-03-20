package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.structure.enums.TicketType;

import java.util.Date;

public class Ticket {
    private String code;
    private Seat seat;
    private Event event;
    private TicketType type;

    public Ticket(String code, Seat seat, Event event, TicketType type) {
        this.code = code;
        this.seat = seat;
        this.event = event;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public Event getEvent() {
        return event;
    }

    public Seat getSeat() {
        return seat;
    }

    public TicketType getType() {
        return type;
    }
    //Format: EVENTNAME_DATE_ROW_NUM_TYPE_RANDOM
    public static String generateTicketCode(Ticket ticket) {
        String eventName = ticket.getEvent().getName().replaceAll("\\s+", "").toUpperCase();
        String eventDate = new java.text.SimpleDateFormat("yyyyMMdd").format(ticket.getEvent().getDate());
        int row = ticket.getSeat().getRow();
        int num = ticket.getSeat().getNumber();
        String type = ticket.getType().name().substring(0, 3).toUpperCase();


        int randomSuffix = (int) (Math.random() * 900 + 100);

        return String.format("%s_%s_R%dC%d_%s_%d", eventName, eventDate, row, num, type, randomSuffix);
    }
}
