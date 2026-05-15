package bg.tu_varna.sit.f24621726.commands.main_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Seat;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;

public class FreeSeatsCommand extends Command {

    public FreeSeatsCommand() {
        super("freeseats", "freeseats <date> <name>",
                "Outputs information about available seats for event with name <name>\n" +
                        "and on date <date>.", CommandType.MAIN);
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

        int currentRow = -1;

        for (Seat seat : freeSeats) {
            if (seat.getRow() != currentRow) {
                currentRow = seat.getRow();
                System.out.print("\nRow " + currentRow + ": ");
            }
            System.out.print(seat.getNumber() + " ");
        }
        System.out.println();
    }


}