package Model.Domain;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public abstract class Animal {

    /**
     * Идентификатор животного
     */
    protected int animalId;

    /**
     * Кличка животного
     */
    protected String nickname;

    /**
     * День рождения животного
     */
    protected Date birthday;

    /**
     * Формат даты
     */
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Список команд животного типа ArrayList<Command>
     */
    protected ArrayList<Command> commands = new ArrayList<>();

    /**
     * Получить идентификатор животного типа int
     * @return идентификатор животного типа int
     */
    public int getAnimalId() {
        return animalId;
    }

    /**
     * Возвращает кличку животного типа String
     * @return кличку животного типа String
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Возвращает день рождения животного типа Date
     * @return день рождения животного типа Date
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Получить текущий формат даты типа SimpleDateFormat
     * @return текущий формат даты типа SimpleDateFormat
     */
    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Получить текущий формат даты типа String
     * @return текущий формат даты типа String
     */
    public static String getDateFormatPattern() {
        return dateFormat.toPattern();
    }

    /**
     * Установить текущий формат даты типа SimpleDateFormat
     * @param dateFormat текущий формат даты типа SimpleDateFormat
     */
        public static void setDateFormat(SimpleDateFormat dateFormat) {
        Animal.dateFormat = dateFormat;
    }

    /**
     * Какой звук издает животное
     * @return звук в текстовом формате
     */
    public String response() {
        return "Это животное говорит: ";
    }

    /**
     * Возвращает список команд животного типа ArrayList<Command>
     * @return список команд животного типа ArrayList<Command>
     */
    public ArrayList<Command> getCommands() {
        return commands;
    }

    /**
     * Устанавливает список команд животного типа ArrayList<Command>
     * @param commands список команд животного типа ArrayList<Command>
     */
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор животного
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     */
    public Animal(int animalId, String nickname, Date birthday) {
        this.animalId = animalId;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    /**
     * Представление команд, добавленные животному
     * @return String представление команд, добавленные животному в строковом формате
     */
    public String allCommandsToString() {
        StringBuilder sb = new StringBuilder("Команды:");

        if (this.commands.isEmpty())
            return sb.append("---").toString();
        else
            sb.append("\n");

        for (int i = 0; i < this.commands.size(); i++) {
            sb.append("\t").append((i + 1)).append(" ").append(this.commands.get(i));
            if (i != this.commands.size() - 1) sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Выводит представление команд, добавленные животному в строковом формате
     */
    public void showCommands() {
        System.out.print(allCommandsToString());
    }

    /**
     * Возвращает команду типа Command по имени команды commandName типа String
     * @param commandName имя команды типа String
     * @return команду типа Command или null, если команда не найдена
     */
    public Command getCommand(String commandName) {
        for (Command command: this.commands)
            if (Objects.equals(command.getCommandName().toLowerCase(), commandName.toLowerCase())) return command;
        return null;
    }

    /**
     * Возвращает команду типа Command по индексу команды index типа int (счет идет с 1)
     * @param index индекс команды index типа int
     * @return команду типа Command или null, если команда не найдена
     */
    public Command getCommand(int index) {
        index--; // Внешний аргумент начинается с 1, а индексация ArrayList c 0
        if (index >= 0 && index < this.commands.size()) return this.commands.get(index);
        return null;
    }

    /**
     * Добавляет команду command типа Command, добавляемую животному
     * @param command типа Command команда, добавляемую животному
     * @return command типа Command команда, добавленная животному или null, если команда уже добавлена
     */
    public Command learnCommand(Command command) {
        if(!this.commands.contains(command)) {
            this.commands.add(command);
            return command;
        }
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по индексу
     * @param index типа int индекс удаляемой команды
     * @return удаленную команду типа Command или null, если индекс некорректен
     */
    public Command forgetCommand(int index) {
        if (index >= 0 && index < this.commands.size()) return this.commands.remove(index);
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд типа ArrayList<Command> по имени
     * @param commandName типа String имя удаляемой команды
     * @return удаленную команду типа Command или null, если команда по имени не найдена
     */
    public Command forgetCommand(String commandName) {
        Command command = getCommand(commandName);
        if (command != null) return forgetCommand(command);
        return null;
    }

    /**
     * Удалить команду command типа Command из списка команд животного типа ArrayList<Command> по команде
     * @param command команда типа Command
     * @return удаленную команду типа Command или null, если команда типа Command не найдена
     */
    public Command forgetCommand(Command command) {
        if (this.commands.remove(command)) return command;
        return null;
    }

    /**
     * Краткое представление объекта Animal
     * @return String c кратким описанием (идентификатор + кличка + дата рождения) объекта типа Animal
     */
    public String shortDescription() {
//        return "ID: " + this.animalId + ", кличка: " + this.nickname.replaceAll(" ", "_") + ", день рождения: " + dateFormat.format(this.birthday);
        return "ID: " + this.animalId + ", кличка: " + this.nickname + ", день рождения: " + dateFormat.format(this.birthday);
    }

    /**
     * Переопределенное представление объекта Animal
     * @return String c описание объекта типа Animal
     */
    @Override
    public String toString() {
        String description = shortDescription() + "\n";
        if (!this.commands.isEmpty()) description += allCommandsToString() + "\n";
        return description;
    }

    /**
     * Сравнение объектов типа Animal
     * @param o -сравниваемый объект типа Animal
     * @return boolean true если объекты равны по id, по nickname и по дню рождения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(nickname, animal.nickname) && Objects.equals(birthday, animal.birthday);
    }

}