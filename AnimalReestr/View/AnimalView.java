package View;

import Model.AnimalKind;
import Model.Domain.Animal;
import Model.Domain.Command;
import Model.Interfaces.IAnimals;
import Model.Interfaces.ICommands;
import View.Interfaces.IAnimalView;

import java.util.Arrays;
import java.util.Scanner;

public class AnimalView implements IAnimalView {

    /**
     * Отобразить результат команды "выбрать животное" с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    @Override
    public void selectAnimal(int index, Animal animal) {
        System.out.println("Команда \"выбрать животное\":\nВыбрано новое животное:\n" + index + " " + animal.shortDescription());
    }

    /**
     * Отобразить ошибку команды "выбрать животное" (когда список животных пуст)
     */
    @Override
    public void selectAnimalError() {
        System.out.println("Команда \"выбрать животное\":\n");
    }

    /**
     * Отобразить текущее животное с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    @Override
    public void selectedAnimal(int index, Animal animal) {
        System.out.println("\nТекущее животное: " + index + " " + animal.shortDescription());
    }

    /**
     * Отобразить ошибку при отображении текущего животного (когда список животных пуст)
     */
    @Override
    public void selectedAnimalError() {
        System.out.println("Текущее животное не выбрано...");
    }

    /**
     * Отобразить результат команды "следующее животное" с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    @Override
    public void nextAnimal(int index, Animal animal) {
        System.out.println("Команда \"следующее животное\":\nВыбрано новое животное:\n" + index + " " + animal.shortDescription());
    }

    /**
     * Отобразить ошибку команды "следующее животное" (когда список животных пуст)
     */
    @Override
    public void nextAnimalError() {
        System.out.println("Команда \"следующее животное\":\n");
    }

    /**
     * Отобразить результат команды "предыдущее животное" с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    @Override
    public void previousAnimal(int index, Animal animal) {
        System.out.println("Команда \"предыдущее животное\":\nВыбрано новое животное:\n" + index + " " + animal.shortDescription());
    }

    /**
     * Отобразить ошибку команды "предыдущее животное" (когда список животных пуст)
     */
    @Override
    public void previousAnimalError() {
        System.out.println("Команда \"предыдущее животное\":\n");
    }

    /**
     * Отобразить результат команды "добавлено животное" с описанием животного
     * @param animal добавленное животное типа Animal
     */
    @Override
    public void addAnimal(Animal animal) {
        System.out.println("Команда \"добавить животное\":\n" + animal);
    }

    /**
     * Отобразить ошибку команды "добавить животное" (когда были представлены некорректные данные животного: кличка, день рождения или специфические параметры)
     */
    @Override
    public void addAnimalError() {
        System.out.println("Команда \"добавить животное\":\nЖивотное не создано...");
    }

    /**
     * Отобразить результат команды "удалено животное" с описанием животного
     * @param animal - удаляемое животное типа Animal
     */
    @Override
    public void removeAnimal(Animal animal) {
        if (animal != null)
            System.out.println("Команда \"удалить животное\":\nУдалено животное:\n" + animal);
        else
            System.out.println("Команда \"удалить животное\":\nживотное не удалено");
    }

    /**
     * Отобразить ошибку команды "удалить животное" (когда список животных пуст)
     */
    @Override
    public void removeAnimalError() {
        System.out.println("Команда \"удалить животное\":\n");
    }

    /**
     * Отобразить результат команды "выучить команду животным" с описанием животного и описанием команд животного
     * @param animal животное типа Animal, которое изучило команду типа Command
     * @param command команда типа Command, которую изучило животное типа Animal
     */
    @Override
    public void learnCommand(Animal animal, Command command) {
        if (command != null)
            System.out.println("Команда \"выучить команду животным\":\nЖивотное:\n" + animal.shortDescription() + "\nизучило новую команду\n" + command + "\n" + animal.allCommandsToString());
        else
            System.out.println("Команда \"выучить команду животным\":\nЖивотное:\n" + animal.shortDescription() + "\nне изучило новую команду\n" + animal.allCommandsToString());
    }

    /**
     * Отобразить ошибку команды "выучить команду животным" (когда список животных пуст)
     */
    @Override
    public void learnCommandError() {
        System.out.println("Команда \"выучить команду животным\":\n");
    }

    /**
     * Отобразить результат команды "забыть команду животным" с описанием животного и описанием команд животного
     * @param animal животное типа Animal, которое забыло команду типа Command
     * @param command команда типа Command, которую забыло животное типа Animal
     */
    @Override
    public void forgetCommand(Animal animal, Command command) {
        System.out.println("Команда \"забыть команду животным\":\nЖивотное:\n" + animal.shortDescription() + "\nзабыло команду\n" + command + "\n" + animal.allCommandsToString());
    }

    /**
     * Отобразить ошибку команды "забыть команду животным" (когда список животных пуст)
     */
    @Override
    public void forgetCommandError() {
        System.out.println("Команда \"забыть команду животным\":\n");
    }

    /**
     * Отобразить результат команды "команды животного" с описанием животного и его команд
     * @param animal животное, команды которого необходимо отобразить типа Animal
     */
    @Override
    public void showCommands(Animal animal) {
        System.out.println("Команда \"команды животного\":\nЖивотное:\n" + animal.shortDescription() + "\nзнает\n" + animal.allCommandsToString());
    }

    /**
     * Отобразить ошибку команды "команды животного" (когда список животных пуст)
     */
    @Override
    public void showCommandsError() {
        System.out.println("Команда \"команды животного\":\nЖивотное:\n");
    }

    /**
     * Отобразить результат команды "список всех команд" из общего списка команд типа Commands
     * @param commands - общий список команд типа Commands
     */
    @Override
    public void showAllCommands(ICommands commands) {
        System.out.println(commands);
    }

    /**
     * Отобразить результат команды "выучить команду животным" если такой команды не существует в общем списке команд
     * @param command команда типа Command, которое пытается изучить животное типа Animal
     */
    @Override
    public void notExistingInAllCommands(String command) {
        System.out.println(command + " не существует в общем списке команд...");
    }

    /**
     * Отобразить результат команды "забыть команду животным" если такой команды не существует в списке команд животного
     * @param command команда типа Command, которое пытается изучить животное типа Animal
     */
    @Override
    public void notExistingInAnimalCommands(String command) {
        System.out.println(command + " не существует в списке команд животного...");
    }

    /**
     * Отобразить результат команды "добавить команду" в список всех команд
     * @param command - добавляемая команда типа Command
     */
    public void addCommand(Command command){
        if(command != null)
            System.out.println("Команда \"добавить команду в список всех команд\":\nДобавлена новая " + command + "\n");
        else
            System.out.println("Команда \"добавить команду в список всех команд\":\nНовая команда не добавлена...\n");
    }

    /**
     * Отобразить результат команды "удалить команду" из списка всех команд
     * @param command - удаляемая команда типа Command
     */
    @Override
    public void removeCommand(Command command) {
        if(command != null)
            System.out.println("Команда \"удалить команду из списка всех команд\":\nУдалена " + command + "\n");
        else
            System.out.println("Команда \"удалить команду из списка всех команд\":\nКоманда не удалена...\n");
    }

    /**
     * Показать всех животных в списке
     * @param animals типа IAnimals список животных
     */
    @Override
    public void showAllAnimals(IAnimals animals) {
        if (!animals.getAnimals().isEmpty())
            System.out.println("Команда: \"показать всех животных\":\n" + animals);
        else
            System.out.println("Список животных пуст\nКоманда: \"показать всех животных\":\n");
    }

    /**
     * МЕНЮ
     */
    @Override
    public void showMenu() {
        System.out.println("\n==========================================================================MENU========================================================================");
          System.out.println("| Животные: aa-Все|as-Выбрать|na-Следующее|pa-Предыдущее|an-Добавить|ad-Удалить (первое встреченное)|ac-Команды|al-Выучить команду|af-Забыть команду |");
          System.out.println("| Команды: ca-Все|cn-Добавить|cd-Удалить                                                                                                             |");
          System.out.println("| Выход: q                                                                                                                                           |");
          System.out.println("==========================================================================MENU========================================================================");
    }

    /**
     * Пользовательский ввод
     * @param prompt сообщение пользователю
     * @return пользовательский ввод типа String
     */
    public String prompt(String prompt) {
        System.out.print(prompt);
        Scanner s = new Scanner(System.in);
        String result = s.nextLine();
        if (result.isBlank()) System.out.println("Пустой ввод");
        return result;
    }

    /**
     * Выбор вида животного для создания
     * @param message сообщение пользователю
     * @return вид животного типа AnimalKind или null при отказе от создания (пользователь ввел пустую строку)
     */
    public AnimalKind strToAnimalKind(String message) {
        String str;
        do {
            str = prompt(message);
            try {
                return AnimalKind.valueOf(str.toUpperCase());
            } catch (IllegalArgumentException ex){
                if (str.isBlank())
                    System.out.println("Отказ от создания животного\n");
                else
                    System.out.printf("Такой вид животных не предусмотрен! Попробуйте еще раз (%s)! \n", Arrays.toString(AnimalKind.values()));
            }
        } while (!str.isEmpty());
        return null;
    }

    /**
     * Ввод специфических свойств животного
     * @param message сообщение пользователю
     * @return значение специфических свойств животных типа int
     */
    public int strToIntForAnimalsCreate (String message) {
        String str;
        do {
            str = prompt(message);
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                if (str.isEmpty())
                    System.out.println("Значение по умолчанию 50!");
                else
                    System.out.println("\"" + str + "\" не число! Попробуйте еще раз!");
            }
        } while (!str.isEmpty());
        return 50;
    }

    /**
     * Сообщение о начале завершения работы программы
     */
    @Override
    public void finishing() {
        System.out.println("Завершение работы программы...");
    }

    /**
     * Сообщение о конце завершения работы программы
     */
    @Override
    public void finished() {
        System.out.println("Работа программы успешно завершена...");
    }

    /**
     * Сообщение о неопознанной команде
     */
    @Override
    public void unknownCommand() {
        System.out.println("Неопознанная команда...\n");
    }
}
