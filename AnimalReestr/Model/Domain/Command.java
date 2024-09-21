package Model.Domain;

import java.util.Objects;

public class Command {

    /**
     * commandName типа String имя создаваемой команды
     */
    private String commandName;

    /**
     * Конструктор
     * @param commandName типа String имя создаваемой команды
     */
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Получить имя команды
     * @return commandName имя команды типа String
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Переопределенное представление объекта Command
     * @return String Команда: имя команды
     */
    @Override
    public String toString() {
        return "команда: " + this.commandName;
    }

    /**
     * Переопределенная процедура сравнения объектов типа Command
     * @param o сравниваемый объект типа Command
     * @return boolean (true если названия объектов одинаковы)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(commandName.toLowerCase(), command.commandName.toLowerCase());
    }

}
