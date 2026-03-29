package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.Hall;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;



public interface Command {
    boolean matches(String input);
    void execute(String input, TicketSystem system) throws Exception;
}
