package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.Seat;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;

public class FreeSeatsCommand extends Command {

    public FreeSeatsCommand() {
        super("freeseats", "freeseats <date> <name>",
                "Outputs information about available seats for event with name <name>\n" +
                        "and on date <date>.");
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 3,3);

        Date date = java.sql.Date.valueOf(args.get(1));
        String name = args.get(2);

        List<Seat> freeSeats = system.freeSeats(date, name);

        if (freeSeats.isEmpty()) {
            System.out.println("No free seats.");
            return;
        }

        for (Seat s : freeSeats) {
            System.out.println("Row: " + s.getRow() + " Seat: " + s.getNumber());
        }
    }


}