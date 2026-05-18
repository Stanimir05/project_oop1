package bg.tu_varna.sit.f24621726.commands.main_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Seat;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;
/**
 * Команда за извеждане на резервирани,
 * но незакупени места.
 *
 * Командата позволява:
 * - търсене по дата
 * - търсене по име на събитие
 * - комбинирано търсене
 */
public class BookingsCommand extends Command {

    public BookingsCommand() {
        super("bookings", "bookings [<date>] [<eventName>]",
                "Outputs information about booked but not bought tickets for event with name <name>\n" +
                        "on date <date>. If <name> is missing it outputs information about all events on <date>.\n" +
                        "If <date> is missing it outputs information about all dates for that event.", CommandType.MAIN);

    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 2, 3);

        Date date = null;
        String eventName = null;

        if (args.size() == 2) {
            if (isDate(args.get(1))) {
                date = java.sql.Date.valueOf(args.get(1));
            } else {
                eventName = args.get(1);
            }
        } else if (args.size() == 3) {
            date = java.sql.Date.valueOf(args.get(1));
            eventName = args.get(2);
        }

        List<Seat> bookings = system.bookings(date, eventName);

        if (bookings.isEmpty()) {
            System.out.println("No booked seats found.");
            return;
        }

        for (Seat seat : bookings) {
            System.out.println("Row: " + seat.getRow() + " Seat: " + seat.getNumber());
        }
    }

    private boolean isDate(String value) {
        try {
            java.sql.Date.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}