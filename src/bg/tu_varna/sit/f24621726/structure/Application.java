package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.commands.comand_manager.CommandManager;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketSystem system = new TicketSystem();
        CommandManager manager = new CommandManager();
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            manager.process(input, system);
        }

        scanner.close();
    }


    }

