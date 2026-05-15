package bg.tu_varna.sit.f24621726.commands.extra_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Event;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class UndersoldCommand extends Command {

    public UndersoldCommand() {
        super(
                "undersold",
                "undersold <percent>",
                "Gives all events with a selling rate under <percent> percent"
        , CommandType.EXTRA);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {

        validateArgs(args, 2, 2);

        int percent = Integer.parseInt(args.get(1));

        List<Event> undersold = system.eventsUnderPercent(percent);

        if (undersold.isEmpty()) {
            System.out.println(
                    "No events under " + percent + "% sold."
            );
            return;
        }

        System.out.println(system.displayEvents(undersold));
        System.out.println("If you want to remove undersold events use command removeunder <percent>");
    }
}