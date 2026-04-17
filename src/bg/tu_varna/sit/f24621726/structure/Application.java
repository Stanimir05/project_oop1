package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.commands.CommandManager;

import java.util.Scanner;

public class Application {
    static void main(String[] args) {
        Hall hall1 = new Hall(10, 6, 1);
        Hall hall2 = new Hall(15, 10, 2);
        Hall hall3 = new Hall(18, 10, 3);

        TicketSystem system = new TicketSystem();
        system.addHall(hall1);
        system.addHall(hall2);
        system.addHall(hall3);


        CommandManager manager=new CommandManager();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Ticket system started. Type 'exit' to quit.");
        while(true)
        {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                manager.process(input, system);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        }


    }

