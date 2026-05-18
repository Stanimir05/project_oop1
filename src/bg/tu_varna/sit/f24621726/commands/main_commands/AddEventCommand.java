package bg.tu_varna.sit.f24621726.commands.main_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;
/**
 * Команда за добавяне на ново събитие.
 *
 * Командата създава събитие
 * с:
 * - дата
 * - зала
 * - име
 */
public class AddEventCommand extends Command {
    public AddEventCommand() {
        super("addevent", "addevent <date> <hall> \"<name>\"",
                "Adds a new event on date <date> with a name <name> in hall with a number <hall.", CommandType.MAIN);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 4,4);

        Date date = java.sql.Date.valueOf(args.get(1));
        int hallNumber = Integer.parseInt(args.get(2));
        String name = joinFrom(args, 3);

        system.addEvent(date, hallNumber, name);
        System.out.println("Event added successfully!");
    }

}
