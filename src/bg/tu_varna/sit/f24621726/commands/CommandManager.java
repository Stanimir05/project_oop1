package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<Command> commands = new ArrayList<>();

    public CommandManager() {
        commands.add(new BookCommand());

    }

    public void process(String input, TicketSystem system) throws Exception {
        for (Command cmd : commands) {
            if (cmd.matches(input)) {
                cmd.execute(input, system);
                return;
            }
        }

        System.out.println("Unknown command!");
    }
}
