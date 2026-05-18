    package bg.tu_varna.sit.f24621726.commands.file_commands;

    import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
    import bg.tu_varna.sit.f24621726.enums.CommandType;
    import bg.tu_varna.sit.f24621726.files.FileSystem;
    import bg.tu_varna.sit.f24621726.structure.TicketSystem;

    import java.util.List;
    /**
     * Команда за отваряне на файл.
     *
     * Командата зарежда данните от файл
     * чрез класа FileSystem.
     *
     * След успешно отваряне:
     * - се зареждат залите
     * - се зареждат събитията
     * - системата се маркира като отворена
     */
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
