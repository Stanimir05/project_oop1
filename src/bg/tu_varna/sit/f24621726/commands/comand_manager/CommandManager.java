package bg.tu_varna.sit.f24621726.commands.comand_manager;

import bg.tu_varna.sit.f24621726.commands.extra_commands.*;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpExCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpfCommand;
import bg.tu_varna.sit.f24621726.commands.extra_commands.help.HelpmCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.OpenCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.SaveCommand;
import bg.tu_varna.sit.f24621726.commands.file_commands.SaveasCommand;
import bg.tu_varna.sit.f24621726.commands.main_commands.*;
import bg.tu_varna.sit.f24621726.exceptions.AccessDeniedException;
import bg.tu_varna.sit.f24621726.exceptions.InvalidArgumentsException;
import bg.tu_varna.sit.f24621726.exceptions.NotFoundException;
import bg.tu_varna.sit.f24621726.files.FileSystem;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.rmi.registry.Registry;
import java.util.*;
/**
 * Клас, който управлява всички потребителски команди.
 *
 * Отговаря за:
 * - регистриране на командите
 * - разпознаване на въведена команда
 * - разделяне на входа на аргументи
 * - проверка за отворен файл
 * - изпълнение на съответната команда
 * - централизирана обработка на грешки
 */
public class CommandManager {
    /**
     * Обект за работа с файловата система.
     */
    private FileSystem fileSystem = new FileSystem();
    /**
     * Колекция с всички налични команди.
     *
     * Ключът е името на командата,
     * а стойността е обектът команда.
     */
    private Map<String, Command> commands = new HashMap<>();
    /**
     * Създава CommandManager и регистрира
     * всички команди в системата.
     */
    public CommandManager() {
        register(new BookCommand());
        register(new AddEventCommand());
        register(new FreeSeatsCommand());
        register(new UnbookCommand());
        register(new BuyCommand());
        register(new BookingsCommand());
        register(new CheckCommand());
        register(new ReportCommand());
        register(new DisplayHallsCommand());
        register(new DisplayEventsCommand());
        register(new TopeventsCommand());
        register(new UndersoldCommand());
        register(new RemoveunderCommand());
        register(new OpenCommand(fileSystem));
        register(new SaveCommand(fileSystem));
        register(new SaveasCommand(fileSystem));
        register(new HelpCommand(commands,fileSystem));
        register(new HelpfCommand(commands));
        register(new HelpExCommand(commands));
        register(new HelpmCommand(commands));
    }

    private void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }
    /**
     * Обработва потребителски вход.
     *
     * Методът:
     * - разделя входа на аргументи
     * - намира съответната команда
     * - проверява дали файл е отворен
     * - изпълнява командата
     * - прихваща и извежда грешки
     *
     * @param input въведеният текст от потребителя
     * @param system системата за билети
     */
    public void process(String input, TicketSystem system) {
        try {
            List<String> args = tokenize(input);
            System.out.flush();
            if (args.isEmpty()) {
                throw new InvalidArgumentsException("No arguments!");
            }

            String commandName = args.get(0).toLowerCase();
            Command command = commands.get(commandName);

            if (command == null) {
                    throw new NotFoundException("Unknown command!");
            }

            if (!command.getName().equals("open")
                    && !command.getName().equals("help")
                    && !fileSystem.isOpened()) {
                throw new AccessDeniedException("No access to commands! Use open first!");

            }

            command.execute(args, system);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Разделя входния текст на аргументи.
     *
     * Поддържа аргументи в кавички,
     * за да могат имена и бележки
     * да съдържат интервали.
     *
     * Пример:
     * book 1 2 2026-06-10 "Rock Festival" "VIP guest"
     *
     * се преобразува до:
     * [book, 1, 2, 2026-06-10, Rock Festival, VIP guest]
     *
     * @param input въведеният текст
     * @return списък с аргументи
     * @throws IllegalArgumentException
     *         ако кавичките не са затворени
     */
    private List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();

        if (input == null || input.trim().isEmpty()) {
            return tokens;
        }
        StringBuilder current = new StringBuilder();
        //флаг за кавички
        boolean insideQuotes = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            //при първо срещане-ture, при второ-false
            if (c == '"') {
                insideQuotes = !insideQuotes;
            }
            // добавяме думата ако е достигнат краят и и сме извън кавички
            else if (c == ' ' && !insideQuotes) {

                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            }
            else {
                current.append(c);
            }
        }

        // последна дума
        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        // незатворени кавички
        if (insideQuotes) {
            throw new IllegalArgumentException("Unclosed quotes in command.");
        }

        return tokens;
    }

}