package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;

public class AddEventCommand implements Command{

    @Override
    public String getName() {
        return "addevent ";
    }

    @Override
    public void execute(String input, TicketSystem system) throws Exception {
        String[] args = input.trim().split("\\s+");

        if (args.length < 4) {
            throw new IllegalArgumentException("Usage: addevent <date> <hall> <name>");
        }

        Date date = java.sql.Date.valueOf(args[1]);
        int hallNumber = Integer.parseInt(args[2]);

        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            if (i > 3) {
                builder.append(" ");
            }
            builder.append(args[i]);
        }

        String name = builder.toString();

        system.addEvent(date, hallNumber, name);
        System.out.println("Event added successfully!");
    }

}
