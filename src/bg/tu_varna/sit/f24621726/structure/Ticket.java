package bg.tu_varna.sit.f24621726.structure;



public class Ticket {
    private String code;
    private Seat seat;
    public Ticket(Seat seat,Event event) {
        this.seat = seat;
        this.code = generateTicketCode(event,seat);
    }
    //конструктор за зареждане на Билет от файла, чийто код е вече генериран
    public Ticket(String code, Seat seat) {
        this.code = code;
        this.seat = seat;
    }
    public String getCode() {
        return code;
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

    @Override
    public String toString() {
        return "Ticket" +
                "code='" + code + '\'' +
                ", seat=" + seat ;
    }
}
