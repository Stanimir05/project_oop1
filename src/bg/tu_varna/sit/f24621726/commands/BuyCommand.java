package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.Ticket;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;

public class BuyCommand extends Command {

    public BuyCommand() {
        super("buy", "buy <row> <seat> <date> \"<name>\"","" +
                "Buys a ticket for event with name <name> on date <date>, row <row> and seat <seat>.");
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 5,5);

        int row = Integer.parseInt(args.get(1));
        int seat = Integer.parseInt(args.get(2));
        Date date = java.sql.Date.valueOf(args.get(3));
        String eventName = joinFrom(args, 4);

        Ticket ticket = system.buy(row, seat, date, eventName);
        System.out.println("Bought successfully! Ticket code: " + ticket.getCode());
    }
}