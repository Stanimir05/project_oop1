package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Map;
import java.util.List;

public class HelpCommand extends Command {

    private Map<String, Command> commands;

    public HelpCommand(Map<String, Command> commands) {
        super("help", "help", "Displays all available commands");
        this.commands = commands;
    }

    @Override
    public void execute(List<String> args, TicketSystem system) {
        System.out.println("Available commands:\n");

        for (Command cmd : commands.values()) {
            System.out.println(cmd.getUsage());
            System.out.println("  -> " + cmd.getDescription());
            System.out.println();
        }
    }
}