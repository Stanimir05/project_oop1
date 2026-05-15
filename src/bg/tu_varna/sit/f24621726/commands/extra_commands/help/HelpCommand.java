package bg.tu_varna.sit.f24621726.commands.extra_commands.help;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.files.FileSystem;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.io.File;
import java.util.Map;
import java.util.List;

public class HelpCommand extends Command {

    private Map<String, Command> commands;
    private FileSystem fileSystem;
    public HelpCommand(Map<String, Command> commands, FileSystem fileSystem) {
        super("help", "help", "Displays all available commands", CommandType.EXTRA);
        this.commands = commands;
        this.fileSystem=fileSystem;
    }

    @Override
    public void execute(List<String> args, TicketSystem system) {
        System.out.println("Available commands:\n");
        if(!fileSystem.isOpened()){
            System.out.println("You cant access commands before opening a file! Use:");
            System.out.println("open <filepath> to open a file!");
            return;
        }



        for (Command cmd : commands.values()) {
            System.out.println(cmd.getUsage());
            System.out.println("  -> " + cmd.getDescription());
            System.out.println();
        }
    }
}