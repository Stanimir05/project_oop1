package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        Command bookCommand = new BookCommand();
        commands.put(bookCommand.getName(), bookCommand);
    }

    public void process(String input, TicketSystem system) throws Exception {
        String[] parts = input.split(" ");
        String commandName = parts[0];

        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(input, system);
        } else {
            System.out.println("Unknown command!");
        }
    }
}