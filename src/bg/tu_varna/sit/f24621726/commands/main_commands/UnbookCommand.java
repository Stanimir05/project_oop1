package bg.tu_varna.sit.f24621726.commands.main_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;
/**
 * Команда за отмяна на резервация.
 *
 * Командата премахва резервация
 * за конкретно място
 * на определено събитие.
 */
public class UnbookCommand extends Command {

    public UnbookCommand() {
        super("unbook", "unbook <row> <seat> <date> \"<name>\"",
                        "Cancels reservation for event with name <name> on date " +
                                "<date> on seat<seat> and row <row>", CommandType.MAIN);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 5,5);

        int row = Integer.parseInt(args.get(1));
        int seat = Integer.parseInt(args.get(2));
        Date date = java.sql.Date.valueOf(args.get(3));
        String eventName = joinFrom(args, 4);

        system.unbook(row, seat, date, eventName);
        System.out.println("Unbooked successfully!");
    }
}