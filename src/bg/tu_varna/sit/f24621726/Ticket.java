package bg.tu_varna.sit.f24621726;

import java.util.Date;

public class Ticket {
    private String code;
    private int row;
    private int seat;
    private TicketType type;
    private Date date;

    public Ticket(int seat, TicketType type, Date date, int row, String code) {
        this.seat = seat;
        this.type = type;
        this.date = date;
        this.row = row;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public TicketType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
