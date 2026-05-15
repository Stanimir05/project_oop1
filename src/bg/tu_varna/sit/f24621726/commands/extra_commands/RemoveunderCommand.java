package bg.tu_varna.sit.f24621726.commands.extra_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Event;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class RemoveunderCommand extends Command {
    public RemoveunderCommand() {
        super("removeunder", "removeunder <percent>",
                "Removes events under <percent> percent sold tickets", CommandType.EXTRA);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args,2,2);
        int percentage=Integer.parseInt(args.get(1));
        List <Event> eventsToRemove=system.eventsUnderPercent(percentage);
        if(eventsToRemove.isEmpty())
        {
            System.out.println("No events under "+percentage+" percent");
            return;
        }
        system.removeEventsByList(eventsToRemove);
        System.out.println("Events under "+percentage+" percent removed from system");
    }
}
