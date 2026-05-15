package bg.tu_varna.sit.f24621726.structure;

public class Hall {
    private int number;
    private int NumberOfRows;
    private int seatsPerRow;

    public Hall( int number,int seatsPerRow, int numberOfRows) {
        this.number = number;
        this.seatsPerRow = seatsPerRow;
        NumberOfRows = numberOfRows;

    }

    @Override
    public String toString() {
        return "Hall " +
                number +
                ", Number of rows=" + NumberOfRows +
                ", seats per row=" + seatsPerRow ;
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
