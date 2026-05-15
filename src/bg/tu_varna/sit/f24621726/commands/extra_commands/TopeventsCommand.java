package bg.tu_varna.sit.f24621726.commands.extra_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Event;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class TopeventsCommand extends Command {
    public TopeventsCommand() {
        super("topevents", "topevents",
                "Outputs information about the 10 most watched events in the system", CommandType.EXTRA);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args,1,1);
        List<Event> topevents=system.topEvents();
        if(topevents.isEmpty())
        {
            System.out.println("No events in system");
            return;
        }
        System.out.println(system.displayEvents(topevents));

    }
}
