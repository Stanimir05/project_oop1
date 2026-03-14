package bg.tu_varna.sit.f24621726.structure;

public class Seat {
    private int number;
    private int roll;
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
        this.roll = roll;

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

    public int getRoll() {
        return roll;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "number=" + number +
                ", roll=" + roll +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }
}
