package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.enums.TicketType;

public class Ticket {
    private String code;
    private Seat seat;
    private Event event;


    public Ticket( Seat seat, Event event) {

        this.seat = seat;
        this.event = event;
        this.code = generateTicketCode(event,seat);
        event.addTicket(this);
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

    public static String generateTicketCode(Event event, Seat seat) {
        String eventDate = new java.text.SimpleDateFormat("yyyyMMdd")
                .format(event.getDate());
        int row = seat.getRow();
        int num = seat.getNumber();
        String uuidPart = java.util.UUID.randomUUID()
                .toString().substring(0, 6).toUpperCase();
        return String.format("%s_R%dS%d_%s",
                eventDate, row, num,  uuidPart);
    }
}
