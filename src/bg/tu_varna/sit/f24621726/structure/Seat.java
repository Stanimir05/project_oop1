package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.structure.enums.SeatStatus;

public class Seat {
    private int number;
    private int row;
    private SeatStatus status;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }


    public Seat(int number, int roll) {
        this.number = number;
        this.row = roll;

        //при създаване мястото по подразбиране е свободно и без бележка
        status=SeatStatus.FREE;
        note=null;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", roll=" + row +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }
    public boolean isFree() {
        return status == SeatStatus.FREE;
    }

    public boolean isBooked() {
        return status == SeatStatus.BOOKED;
    }

    public boolean isBought() {
        return status == SeatStatus.BOUGHT;
    }

}
