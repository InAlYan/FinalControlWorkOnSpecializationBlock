package Model;

import Model.Domain.Command;
import Model.Interfaces.ICommands;

import java.util.ArrayList;
import java.util.Objects;

public class Commands implements ICommands {

    /**
     * Список команд типа ArrayList<Command>
     */
    private ArrayList<Command> commands;

    /**
     * Конструктор
     */
    public Commands() {
        this.commands = new ArrayList<>();
    }

    /**
     * Конструктор
     * @param commands список команд типа ArrayList<Command>
     */
    public Commands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Получить список всех команд
     * @return ArrayList<Command> список команд типа Command
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Заменить список всех команд внешним списком
     * @param commands типа ArrayList<Command> внешний список команд
     */
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Добавить в список команд новую команду на основании названия commandName типа String
     * @param commandName типа String имя создаваемой команды
     * @return command типа Command если такой объект добавлен, null в противном случае
     */
    public Command addCommand(String commandName) {
        if (commandName != null && !commandName.isEmpty() && commandName.toLowerCase().matches ("[a-zа-я ]+"))
            if (this.getCommand(commandName) == null) {
                Command command = new Command(commandName);
                return addCommand(command);
            }
        return null;
    }

    /**
     * Добавить в список команд новую команду объектом типа Command
     * @param command типа Command
     * @return command типа Command если такой объект добавлен, null в противном случае
     */
    public Command addCommand(Command command) {
        if (command != null)
            if (!this.commands.contains(command)) {
                commands.add(command);
                return command;
            }
        return null;
    }

    /**
     * Получить команду типа Command из списка команд по индексу index типа int (счет идет с 1)
     * @param index индекс нужной команды в списке команд
     * @return команда типа Command из списка команд или null, если индекс некорректен
     */
    public Command getCommand(int index) {
        index--; // Внешний аргумент начинается с 1, а индексация ArrayList c 0
        if (index >= 0 && index < this.commands.size()) return this.commands.get(index);
        return null;
    }

    /**
     * Получить команду типа Command из списка команд по имени commandName типа String
     * @param commandName имя искомой команды типа String
     * @return команда типа Command из списка команд
     */
    public Command getCommand(String commandName) {
        for (Command command: this.commands)
            if (Objects.equals(command.getCommandName().toLowerCase(), commandName.toLowerCase())) return command;
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по индексу
     * @param index типа int (начинается с нуля)
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    public Command removeCommand(int index) {
        index--;
        if (index >= 0 && index < this.commands.size()) return this.commands.remove(index);
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по имени
     * @param commandName типа String имя удаляемой команды
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    public Command removeCommand(String commandName) {
        Command command = getCommand(commandName);
        if (command != null) return removeCommand(command);
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command>
     * @param command команда типа Command
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    public Command removeCommand(Command command) {
        if (this.commands.remove(command)) return command;
        return null;
    }

    /**
     * Переопределенное представление объекта Commands
     * @return String Команды: список команд
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Команды:");
        if (this.commands.isEmpty())
            return sb.append("---\n").toString();
        else
            sb.append("\n----------ВСЕ КОМАНДЫ НАЧАЛО----------\n");
        for (int i=0; i < this.commands.size(); i++)
            sb.append((i + 1)).append(" ").append(this.commands.get(i)).append("\n");
        sb.append("----------ВСЕ КОМАНДЫ КОНЕЦ-----------\n");
        return sb.toString();
    }
}
