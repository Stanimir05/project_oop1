package bg.tu_varna.sit.f24621726.commands.main_commands;

import bg.tu_varna.sit.f24621726.commands.comand_manager.Command;
import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.Seat;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;
/**
 * Команда за проверка на билет.
 *
 * Командата проверява
 * дали билет с даден код съществува
 * и е валиден.
 */
public class CheckCommand extends Command {

    public CheckCommand() {
        super("check", "check <code>",
                "Checks the validity of a ticket by the imputed <code>", CommandType.MAIN);
    }

    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 2, 2);

        String code = args.get(1);
        Seat seat = system.checkTicket(code);

        System.out.println("Valid ticket for row " + seat.getRow() + ", seat " + seat.getNumber());
    }
}