package bg.tu_varna.sit.f24621726.structure;


/**
 * Клас, описващ билет за събитие.
 *
 * Всеки билет съдържа:
 * - уникален код
 * - информация за мястото
 *
 * Кодът на билета се генерира автоматично
 * чрез дата на събитието, позиция на мястото
 * и част от UUID идентификатор.
 */
public class Ticket {
    /**
     * Уникален код на билета.
     */
    private String code;
    /**
     * Създава нов билет и генерира уникален код.
     *
     * @param seat мястото за билета
     * @param event събитието, за което е билетът
     */
    private Seat seat;

    public Ticket(Seat seat,Event event) {
        this.seat = seat;
        this.code = generateTicketCode(event,seat);
    }
    /**
     * Създава билет с вече съществуващ код.
     *
     * Използва се при зареждане на данни от файл.
     *
     * @param code код на билета
     * @param seat мястото за билета
     */
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
    /**
     * Генерира уникален код за билет.
     *
     * Кодът съдържа:
     * - дата на събитието
     * - ред и номер на мястото
     * - част от UUID идентификатор
     *
     * Пример:
     * 20260610_R2S5_A1B2C3
     *
     * @param event събитието
     * @param seat мястото
     * @return генериран код
     */

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
