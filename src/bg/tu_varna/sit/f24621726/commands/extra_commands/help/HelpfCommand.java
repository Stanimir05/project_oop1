package bg.tu_varna.sit.f24621726.commands.extra_commands.help;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;
import java.util.Map;

public class HelpfCommand extends Command {

    private Map<String, Command> commands;

    public HelpfCommand(Map<String, Command> commands) {
        super("helpf", "helpf", "Displays all available FILE commands", CommandType.EXTRA);
        this.commands = commands;
    }

    @Override
    public void execute(List<String> args, TicketSystem system) {
        System.out.println("Available FILE commands:\n");

        for (Command cmd : commands.values()) {
            if(cmd.getCommandType()==CommandType.FILE) {
                System.out.println(cmd.getUsage());
                System.out.println("  -> " + cmd.getDescription());
                System.out.println();
            }
        }
    }
}
