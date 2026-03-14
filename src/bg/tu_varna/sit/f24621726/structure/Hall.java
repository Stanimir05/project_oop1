package bg.tu_varna.sit.f24621726.structure;

public class Hall {
    private int number;
    private int NumberOfRows;
    private int seatsPerRow;

    public Hall(int seatsPerRow, int numberOfRows, int number) {
        this.seatsPerRow = seatsPerRow;
        NumberOfRows = numberOfRows;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfRows() {
        return NumberOfRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }
}
