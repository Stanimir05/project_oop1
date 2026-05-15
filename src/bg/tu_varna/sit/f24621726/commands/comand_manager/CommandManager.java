package bg.tu_varna.sit.f24621726.commands.comand_manager;

import bg.tu_varna.sit.f24621726.commands.extra_commands.*;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpExCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpfCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpmCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.OpenCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.SaveCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.SaveasCommand;
import bg.tu_varna.sit.f24621726.commands.main_commands.*;
import bg.tu_varna.sit.f24621726.files.FileSystem;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.rmi.registry.Registry;
import java.util.*;

public class CommandManager {
    private FileSystem fileSystem = new FileSystem();
    private Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        register(new BookCommand());
        register(new AddEventCommand());
        register(new FreeSeatsCommand());
        register(new UnbookCommand());
        register(new BuyCommand());
        register(new BookingsCommand());
        register(new CheckCommand());
        register(new ReportCommand());
        register(new DisplayHallsCommand());
        register(new DisplayEventsCommand());
        register(new TopeventsCommand());
        register(new UndersoldCommand());
        register(new RemoveunderCommand());
        register(new OpenCommand(fileSystem));
        register(new SaveCommand(fileSystem));
        register(new SaveasCommand(fileSystem));
        register(new HelpCommand(commands,fileSystem));
        register(new HelpfCommand(commands));
        register(new HelpExCommand(commands));
        register(new HelpmCommand(commands));
    }

    private void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public void process(String input, TicketSystem system) {
        try {
            List<String> args = tokenize(input);
            System.out.flush();
            if (args.isEmpty()) {
                System.out.println("No arguments given!");
                return;
            }

            String commandName = args.get(0).toLowerCase();
            Command command = commands.get(commandName);

            if (command == null) {
                System.out.println("Unknown command!");
                return;
            }

            if (!command.getName().equals("open")
                    && !command.getName().equals("help")
                    && !fileSystem.isOpened()) {
                System.out.println("No file is opened. Use open first.");
                return;
            }

            command.execute(args, system);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();

        if (input == null || input.trim().isEmpty()) {
            return tokens;
        }
        StringBuilder current = new StringBuilder();
        //флаг за кавички
        boolean insideQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            //при първо срещане-ture, при второ-false
            if (c == '"') {
                insideQuotes = !insideQuotes;
            }
            // добавяме думата ако е достигнат краят и и сме извън кавички
            else if (c == ' ' && !insideQuotes) {

                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            }
            else {
                current.append(c);
            }
        }

        // последна дума
        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        // незатяорени кавички
        if (insideQuotes) {
            throw new IllegalArgumentException("Unclosed quotes in command.");
        }

        return tokens;
    }

}