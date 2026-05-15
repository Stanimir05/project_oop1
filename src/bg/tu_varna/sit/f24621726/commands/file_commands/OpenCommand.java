    package bg.tu_varna.sit.f24621726.commands.file_commands;

    import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
    import bg.tu_varna.sit.f24621726.enums.CommandType;
    import bg.tu_varna.sit.f24621726.files.FileSystem;
    import bg.tu_varna.sit.f24621726.structure.TicketSystem;

    import java.util.List;

    public class OpenCommand extends Command {
        private FileSystem fileSystem;

        public OpenCommand(FileSystem fileSystem) {
            super("open", "open <path>", "Opens a file", CommandType.FILE);
            this.fileSystem = fileSystem;
        }

        @Override
        public void execute(List<String> args, TicketSystem system) throws Exception {
            validateArgs(args, 2, 2);

            String filePath = args.get(1);

            fileSystem.open(filePath,system);
            System.out.println("Successfully opened " + filePath);
        }
    }
