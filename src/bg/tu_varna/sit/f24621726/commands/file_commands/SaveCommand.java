package bg.tu_varna.sit.f24621726.commands.file_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.files.FileSystem;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public class SaveCommand extends Command {
    private FileSystem fileSystem;

    public SaveCommand(FileSystem fileSystem) {
        super("save", "save", "Saves current file", CommandType.FILE);
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 1, 1);

        fileSystem.save(system);

        System.out.println("Saved successfully.");
    }
}
