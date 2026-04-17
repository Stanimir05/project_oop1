package bg.tu_varna.sit.f24621726.commands;

import bg.tu_varna.sit.f24621726.structure.Event;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportCommand extends Command {


    public ReportCommand() {
        super("report", "report <from> <to> [hall]",
                "Outputs information about bought tickets form <from> date to <to> date");
    }


    @Override
    public void execute(List<String> args, TicketSystem system) throws Exception {
        validateArgs(args, 3, 4);

        Date from = java.sql.Date.valueOf(args.get(1));
        Date to = java.sql.Date.valueOf(args.get(2));

        Integer hallNumber = null;
        if (args.size() == 4) {
            hallNumber = Integer.parseInt(args.get(3));
        }

        Map<Event, Integer> report = system.report(from, to, hallNumber);

        if (report.isEmpty()) {
            System.out.println("No events found in the given range.");
            return;
        }

        for (Map.Entry<Event, Integer> entry : report.entrySet()) {
            Event event = entry.getKey();
            int count = entry.getValue();

            System.out.println(
                    "Hall: " + event.getHall().getNumber()
                            + " | Event: " + event.getName()
                            + " | Date: " + event.getDate()
                            + " | Sold tickets: " + count
            );
        }
    }
}