package bg.tu_varna.sit.f24621726.files;

import bg.tu_varna.sit.f24621726.enums.SeatStatus;
import bg.tu_varna.sit.f24621726.structure.*;

import java.io.*;
import java.sql.Date;
import java.util.Map;

public class FileSystem {
    private String currentFilePath;
    private boolean opened;

    public boolean isOpened() {
        return opened;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public void open(String filePath, TicketSystem system) throws Exception {
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
            currentFilePath = filePath;
            opened = true;
            return;
        }

         system.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            loadHalls(reader, system);

        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            loadEvents(reader, system);
        }
        if (system.getEvents().isEmpty() && system.getHalls().isEmpty()) {
            system.loadDefaultData();
        }
        currentFilePath = filePath;
        opened = true;
    }

    public void save(TicketSystem system) throws IOException {
        if (!opened) {
            throw new IllegalStateException("No file is opened.");
        }

        saveAs(currentFilePath, system);
    }

    public void saveAs(String filePath, TicketSystem system) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            writer.println("#HALLS");
            for (Hall hall : system.getHalls().values()) {
                writer.printf("%d,%d,%d%n",
                        hall.getNumber(),
                        hall.getNumberOfRows(),
                        hall.getSeatsPerRow());
            }

            writer.println("#EVENTS");
            for (Event event : system.getEvents()) {
                writer.printf("%s,%s,%d%n",
                        event.getName(),
                        event.getDate(),
                        event.getHallNumber());

                for (Ticket ticket : event.getTickets().values()) {
                    Seat seat = ticket.getSeat();

                    writer.printf("TICKET,%s,%d,%d,%s,%s%n",
                            ticket.getCode(),
                            seat.getNumber(),
                            seat.getRow(),
                            seat.getStatus(),
                            seat.getNote() == null ? "" : seat.getNote());
                }

                writer.println("END_EVENT");
            }
        }

        currentFilePath = filePath;
        opened = true;
    }
    private void loadHalls(BufferedReader reader, TicketSystem system) throws IOException {
        String line;
        boolean inHalls = false;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.equals("#HALLS")) {
                inHalls = true;
                continue;
            }

            if (line.equals("#EVENTS")) {
                break;
            }

            if (inHalls && !line.isEmpty()) {
                String[] parts = line.split(",");

                int number = Integer.parseInt(parts[0]);
                int rows = Integer.parseInt(parts[1]);
                int seatsPerRow = Integer.parseInt(parts[2]);

                Hall hall = new Hall(number, rows, seatsPerRow);
                system.addHall(hall);
            }
        }
    }
    private void loadEvents(BufferedReader reader, TicketSystem system) throws Exception {
        String line;
        boolean inEvents = false;
        Event currentEvent = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.equals("#EVENTS")) {
                inEvents = true;
                continue;
            }

            if (!inEvents || line.isEmpty()) {
                continue;
            }

            if (line.equals("END_EVENT")) {
                currentEvent = null;
                continue;
            }

            String[] parts = line.split(",");

            // Ticket line
            if (parts[0].equals("TICKET")) {
                String code = parts[1];
                int seatNumber = Integer.parseInt(parts[2]);
                int row = Integer.parseInt(parts[3]);
                SeatStatus status = SeatStatus.valueOf(parts[4]);
                String note = parts.length > 5 ? parts[5] : null;

                Seat seat = currentEvent.getSeats()[row][seatNumber];
                seat.setStatus(status);
                seat.setNote(note == null || note.isEmpty() ? null : note);

                Ticket ticket = new Ticket(code, seat);
                currentEvent.addTicket(ticket);
            }

            // Event line
            else {
                String name = parts[0];
                Date date = java.sql.Date.valueOf(parts[1]);
                int hallNumber = Integer.parseInt(parts[2]);

                Hall hall = system.findHall(hallNumber);

                currentEvent = new Event(name, date, hall);
                system.getEvents().add(currentEvent);
            }
        }
    }
}