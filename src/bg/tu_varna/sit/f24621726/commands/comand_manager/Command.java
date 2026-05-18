package bg.tu_varna.sit.f24621726.commands.comand_manager;

import bg.tu_varna.sit.f24621726.enums.CommandType;
import bg.tu_varna.sit.f24621726.structure.TicketSystem;

import java.util.List;
/**
 * Абстрактен базов клас за всички команди в системата.
 *
 * Класът реализира основната структура на Command Pattern.
 *
 * Всяка команда съдържа:
 * - име
 * - синтаксис
 * - описание
 * - тип на командата
 *
 * Класът предоставя:
 * - валидация на аргументи
 * - помощни методи
 * - абстрактен execute() метод
 */
public abstract class Command {
    /**
     * Име на командата.
     */
    private final String name;
    /**
     * Синтаксис на командата.
     */
    private final String usage;
    /**
     * Описание на командата.
     */
    private final String description;
    /**
     * Тип на командата.
     */
    private final CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public Command(String name, String usage, String description, CommandType commandType) {
        this.name = name;
        this.usage = usage;
        this.description = description;
        this.commandType = commandType;
    }

    public String getName() {
        return name;
    }

    public String getUsage() {
        return usage;
    }
    public String getDescription() {
        return description;
    }
    /**
     * Проверява дали броят на аргументите е валиден.
     *
     * @param args списък с аргументи
     * @param minArgs минимален брой аргументи
     * @param maxArgs максимален брой аргументи
     * @throws IllegalArgumentException
     *         при невалиден брой аргументи
     */
    protected void validateArgs(List<String> args, int minArgs, int maxArgs) {
        if (args == null || args.size() < minArgs || args.size() > maxArgs) {
            throw new IllegalArgumentException("Wrong arguments! Usage: " + usage);
        }
    }
    /**
     * Обединява последователни аргументи
     * в един текстов низ.
     *
     * Използва се при:
     * - бележки
     * - имена с интервали
     * - свободен текст
     *
     * @param args списък с аргументи
     * @param startIndex начален индекс
     * @return обединеният текст
     */
    protected String joinFrom(List<String> args, int startIndex) {
        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < args.size(); i++) {
            if (i > startIndex) {
                builder.append(" ");
            }
            builder.append(args.get(i));
        }

        return builder.toString();
    }
    /**
     * Изпълнява командата.
     *
     * Методът се реализира
     * от всички наследници на класа.
     *
     * @param args аргументи на командата
     * @param system системата за билети
     * @throws Exception при грешка при изпълнение
     */
    public abstract void execute(List<String> args, TicketSystem system) throws Exception;



}