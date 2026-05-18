package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.enums.SeatStatus;
/**
 * Клас, описващ място в зала.
 *
 * Всяко място съдържа:
 * - номер на мястото
 * - ред
 * - статус
 * - бележка
 *
 * Статусът определя дали мястото е:
 * - свободно
 * - резервирано
 * - закупено
 */
public class Seat {
    /**
     * Номер на мястото.
     */
    private int number;
    /**
     * Номер на реда.
     */
    private int row;
    /**
     * Текущ статус на мястото.
     */
    private SeatStatus status;
    /**
     * Допълнителна бележка към мястото.
     */
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
