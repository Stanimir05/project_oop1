package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {

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
        register(new HelpCommand(commands));
    }

    private void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public void process(String input, TicketSystem system) {
        try {
            List<String> args = tokenize(input);

            if (args.isEmpty()) return;

            String commandName = args.get(0).toLowerCase();

            Command command = commands.get(commandName);

            if (command != null) {
                command.execute(args, system);
            } else {
                System.out.println("Unknown command!");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();

        if (input == null || input.trim().isEmpty()) {
            return tokens;
        }

        if (hasUnclosedQuotes(input)) {
            throw new IllegalArgumentException("Unclosed quotes in command.");
        }

        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1)); // quoted text
            } else {
                tokens.add(matcher.group(2)); // normal word
            }
        }

        return tokens;
    }

    private boolean hasUnclosedQuotes(String input) {
        int count = 0;

        for (char c : input.toCharArray()) {
            if (c == '"') count++;
        }

        return count % 2 != 0;
    }
}