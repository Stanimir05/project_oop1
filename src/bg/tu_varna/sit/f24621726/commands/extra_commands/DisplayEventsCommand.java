package bg.tu_varna.sit.f24621726.commands.extra_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class DisplayEventsCommand extends Command {
    public DisplayEventsCommand() {
        super("displayevents", "displayevents [<hall>] ",
                "Displays all events in hall with number <hall> \n" +
                        "if <hall> iss missing displays all events in the system", CommandType.EXTRA);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 1, 2);

        String result;

        if (args.size() == 1) {
            result = system.displayEvents(system.getEvents());

            if (result.isEmpty()) {
                System.out.println("No events in the system");
                return;
            }

            System.out.println(result);
            return;
        }

        int hallNumber = Integer.parseInt(args.get(1));

        result = system.displayEvents(system.findEventsByHallNumber(hallNumber));

        if (result.isEmpty()) {
            System.out.println("No events in that hall");
            return;
        }

        System.out.println(result);
    }
}
