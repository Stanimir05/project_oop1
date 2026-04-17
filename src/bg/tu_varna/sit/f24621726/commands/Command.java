package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;

public abstract class Command {
    private final String name;
    private final String usage;
    private final String description;

    public Command(String name, String usage, String description) {
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }


    protected void validateArgs(List<String> args, int minArgs, int maxArgs) {
        if (args == null || args.size() < minArgs || args.size() > maxArgs) {
            throw new IllegalArgumentException("Usage: " + usage);
        }
    }

    protected String joinFrom(List<String> args, int startIndex) {
        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < args.size(); i++) {
            if (i > startIndex) {
                builder.append(" ");
            }
            builder.append(args.get(i));
        }

        return builder.toString();
    }

    public abstract void execute(List<String> args, TicketSystem system) throws Exception;


    public String getDescription() {
        return description;
    }
}