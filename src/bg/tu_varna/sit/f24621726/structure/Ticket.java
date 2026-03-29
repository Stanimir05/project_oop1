package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.structure.enums.TicketType;

import java.util.Date;

public class Ticket {
    private String code;
    private Seat seat;
    private Event event;
    private TicketType type;

    public Ticket(String code, Seat seat, Event event, TicketType type) {

        this.seat = seat;
        this.event = event;
        this.type = type;
        this.code = generateTicketCode(event,seat,type);
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

    public static String generateTicketCode(Event event, Seat seat, TicketType type) {

        String eventDate = new java.text.SimpleDateFormat("yyyyMMdd")
                .format(event.getDate());

        int row = seat.getRow();
        int num = seat.getNumber();

        String fullType = type.name();
        String shortType = fullType.length() >= 3 ? fullType.substring(0, 3) : fullType;

        String uuidPart = java.util.UUID.randomUUID()
                .toString().substring(0, 6).toUpperCase();

        return String.format("%s_R%dC%d_%s_%s",
                eventDate, row, num, shortType, uuidPart);
    }
}
