package bg.tu_varna.sit.f24621726.commands.file_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.files.FileSystem;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;
/**
 * Команда за записване на данните
 * в избран файл.
 *
 * Командата използва FileSystem,
 * за да запише:
 * - зали
 * - събития
 * - билети
 * - статуси на места
 *
 * в нов файл или нова локация.
 */
public class SaveasCommand extends Command {
    private FileSystem fileSystem;
    public SaveasCommand(FileSystem fileSystem) {
        super("saveas", "saveas <filepath>", "Saves current file in a desired location",CommandType.FILE);
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 2, 2);
String filepath=args.get(1);
        fileSystem.saveAs(filepath,system);

        System.out.println("Saved successfully.");
    }
}
