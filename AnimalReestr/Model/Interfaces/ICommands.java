package Model.Interfaces;

import Model.Domain.Command;

import java.util.ArrayList;

public interface ICommands {

    /**
     * Получить список всех команд
     * @return ArrayList<Command> список команд типа Command
     */
    ArrayList<Command> getCommands();

    /**
     * Заменить список всех команд внешним списком
     * @param commands типа ArrayList<Command> внешний список команд
     */
    void setCommands(ArrayList<Command> commands);

    /**
     * Добавить в список команд новую команду на основании названия commandName типа String
     * @param commandName типа String имя создаваемой команды
     * @return command типа Command если такой объект добавлен, null в противном случае
     */
    Command addCommand(String commandName);

    /**
     * Добавить в список команд новую команду объектом типа Command
     * @param command типа Command
     * @return command типа Command если такой объект добавлен, null в противном случае
     */
    Command addCommand(Command command);

    /**
     * Получить команду типа Command из списка команд по индексу index типа int (счет идет с 1)
     * @param index индекс нужной команды в списке команд
     * @return команда типа Command из списка команд или null, если индекс некорректен
     */
    Command getCommand(int index);

    /**
     * Получить команду типа Command из списка команд по имени commandName типа String
     * @param commandName имя искомой команды типа String
     * @return команда типа Command из списка команд
     */
    Command getCommand(String commandName);

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по индексу
     * @param index типа int (начинается с нуля)
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    Command removeCommand(int index);

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по имени
     * @param commandName типа String имя удаляемой команды
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    Command removeCommand(String commandName);

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command>
     * @param command команда типа Command
     * @return возвращает удаленную команду типа Command, если такой команды не обнаружено, то возвращает null
     */
    Command removeCommand(Command command);

}
