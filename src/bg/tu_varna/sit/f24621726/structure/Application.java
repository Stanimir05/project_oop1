package bg.tu_varna.sit.f24621726.structure;

import bg.tu_varna.sit.f24621726.commands.comand_manager.CommandManager;

import java.util.Scanner;
/**
 * Главен клас на приложението.
 *
 * Отговаря за стартирането на системата,
 * създаването на основните обекти и
 * четенето на потребителски команди от конзолата.
 */
public class Application {
    /**
     * Главен метод на програмата.
     *
     * Създава:
     * - Scanner за четене от конзолата
     * - TicketSystem за бизнес логиката
     * - CommandManager за обработка на команди
     *
     * Програмата работи в цикъл, докато потребителят
     * не въведе командата "exit".
     *
     * @param args аргументи от командния ред
     */
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

