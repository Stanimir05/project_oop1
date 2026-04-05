package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;

public class BookCommand implements Command {

    @Override
    public String getName() {
        return "book";
    }

    @Override
    public void execute(String input, TicketSystem system) throws Exception {
        String[] args = input.split(" ");

        int row = Integer.parseInt(args[1]);
        int seat = Integer.parseInt(args[2]);
        Date date = java.sql.Date.valueOf(args[3]);
        String eventName = args[4];
        String note = args.length > 5 ? args[5] : null;

        system.book(row, seat, date, eventName, note);

        System.out.println("Booked successfully!");
    }
}