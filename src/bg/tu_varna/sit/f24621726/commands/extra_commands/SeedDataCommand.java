package bg.tu_varna.sit.f24621726.commands.extra_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class SeedDataCommand extends Command {
    public SeedDataCommand() {
        super("seeddata", "seeddata",
                "Adds mock data to the system if there isn't any", CommandType.EXTRA);
    }

    @Override
    public void execute(List<String> args,
                        TicketSystem system) throws Exception {

        validateArgs(args, 1, 1);

        // проверка дали вече има данни
        if (!system.getEvents().isEmpty()
                || !system.getHalls().isEmpty()) {

            throw new IllegalStateException(
                    "System already contains data!"
            );
        }

        system.loadDefaultData();

        System.out.println("Default data loaded successfully!");
    }
}
