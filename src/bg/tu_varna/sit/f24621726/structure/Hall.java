package bg.tu_varna.sit.f24621726.structure;
/**
 * Клас, описващ зала за провеждане на събития.
 *
 * Всяка зала съдържа:
 * - уникален номер
 * - брой редове
 * - брой места на ред
 */
public class Hall {
    /**
     * Уникален номер на залата.
     */
    private int number;
    /**
     * Брой редове в залата.
     */
    private int NumberOfRows;
    /**
     * Брой места на всеки ред.
     */
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
