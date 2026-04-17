package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.Seat;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class CheckCommand extends Command {

    public CheckCommand() {
        super("check", "check <code>",
                "Checks the validity of a ticket by the imputed <code>");
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 2, 2);

        String code = args.get(1);
        Seat seat = system.checkTicket(code);

        System.out.println("Valid ticket for row " + seat.getRow() + ", seat " + seat.getNumber());
    }
}