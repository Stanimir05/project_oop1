package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.TicketSystem;

public interface Command {
    String getName();
    void execute(String input, TicketSystem system) throws Exception;
}
