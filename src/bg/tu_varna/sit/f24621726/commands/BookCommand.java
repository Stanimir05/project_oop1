package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;

public class BookCommand extends Command {

    public BookCommand() {
        super("book", "book <row> <seat> <date> <name> \"[note]\"",
                "Saves a ticket for event <name> on date <date> on row <row> \n" +
                        "and seat <seat> and adds an optional note \"[note]\".");
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 5,5);

        int row = Integer.parseInt(args.get(1));
        int seat = Integer.parseInt(args.get(2));
        Date date = java.sql.Date.valueOf(args.get(3));
        String eventName = args.get(4);

        String note = null;
        if (args.size() > 5) {
            note = joinFrom(args, 5);
        }

        system.book(row, seat, date, eventName, note);
        System.out.println("Booked successfully!");
    }
}