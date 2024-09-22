package View.Interfaces;

import Model.AnimalKind;
import Model.Domain.Animal;
import Model.Domain.Command;
import Model.Interfaces.IAnimals;
import Model.Interfaces.ICommands;

public interface IAnimalView {

    /**
     * Отобразить результат команды выбрать животное с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    void selectAnimal(int index, Animal animal);

    /**
     * Отобразить ошибку команды "выбрать животное" (когда список животных пуст)
     */
    void selectAnimalError();

    /**
     * Отобразить текущее животное с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    void selectedAnimal(int index, Animal animal);

    /**
     * Отобразить ошибку при отображении текущего животного (когда список животных пуст)
     */
    void selectedAnimalError();

    /**
     * Отобразить результат команды "следующее животное" с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    void nextAnimal(int index, Animal animal);

    /**
     * Отобразить ошибку команды "следующее животное" (когда список животных пуст)
     */
    void nextAnimalError();

    /**
     * Отобразить результат команды "предыдущее животное" с индексом в списке и кратким описанием животного
     * @param index индекс животного в списке типа int
     * @param animal выбранное животное типа Animal
     */
    void previousAnimal(int index, Animal animal);

    /**
     * Отобразить ошибку команды "предыдущее животное" (когда список животных пуст)
     */
    void previousAnimalError();

    /**
     * Отобразить результат команды "добавлено животное" с описанием животного
     * @param animal добавленное животное типа Animal
     */
    void addAnimal(Animal animal);

    /**
     * Отобразить ошибку команды "добавить животное" (когда были представлены некорректные данные животного: кличка, день рождения или специфические параметры)
     */
    void addAnimalError();

    /**
     * Отобразить результат команды "удалено животное" с описанием животного
     * @param animal - удаляемое животное типа Animal
     */
    void removeAnimal(Animal animal);

    /**
     * Отобразить ошибку команды "удалить животное" (когда список животных пуст)
     */
    void removeAnimalError();

    /**
     * Отобразить результат команды "выучить команду животным" с описанием животного и описанием команд животного
     * @param animal животное типа Animal, которое изучило команду типа Command
     * @param command команда типа Command, которое изучило животное типа Animal
     */
    void learnCommand(Animal animal, Command command);

    /**
     * Отобразить ошибку команды "выучить команду животным" (когда список животных пуст)
     */
    void learnCommandError();

    /**
     * Отобразить результат команды "забыть команду животным" с описанием животного и описанием команд животного
     * @param animal животное типа Animal, которое забыло команду типа Command
     * @param command команда типа Command, которую забыло животное типа Animal
     */
    void forgetCommand(Animal animal, Command command);

    /**
     * Отобразить ошибку команды "забыть команду животным" (когда список животных пуст)
     */
    void forgetCommandError();

    /**
     * Отобразить результат команды "команды животного" с описанием животного и его команд
     * @param animal животное, команды которого необходимо отобразить типа Animal
     */
    void showCommands(Animal animal);

    /**
     * Отобразить ошибку команды "команды животного" (когда список животных пуст)
     */
    void showCommandsError();

    /**
     * Отобразить результат команды "список всех команд" из общего списка команд типа Commands
     * @param commands - общий список команд типа Commands
     */
    void showAllCommands(ICommands commands);

    /**
     * Отобразить результат команды "выучить команду животным" если такой команды не существует в общем списке команд
     * @param command наименование команды типа String, которое пытается изучить животное типа Animal
     */
    void notExistingInAllCommands(String command);

    /**
     * Отобразить результат команды "забыть команду животным" если такой команды не существует в списке команд животного
     * @param command команда типа Command, которое пытается изучить животное типа Animal
     */
    void notExistingInAnimalCommands(String command);

    /**
     * Отобразить результат команды "добавить команду" в список всех команд
     * @param command - добавляемая команда типа Command
     */
    void addCommand(Command command);

    /**
     * Отобразить результат команды "удалить команду" из списка всех команд
     * @param command - удаляемая команда типа Command
     */
    void removeCommand(Command command);

    /**
     * Показать всех животных в списке
     * @param animals типа IAnimals список животных
     */
    void showAllAnimals(IAnimals animals);

    /**
     * МЕНЮ
     */
    void showMenu();

    /**
     * Пользовательский ввод
     * @param prompt сообщение пользователю
     * @return пользовательский ввод типа String
     */
    String prompt(String prompt);

    /**
     * Выбор вида животного для создания
     * @param message сообщение пользователю
     * @return вид животного типа AnimalKind или null при отказе от создания (пользователь ввел пустую строку)
     */
    AnimalKind strToAnimalKind(String message);

    /**
     * Ввод специфических свойств животного
     * @param message сообщение пользователю
     * @return значение специфических свойств животных типа int
     */
    int strToIntForAnimalsCreate (String message);

    /**
     * Сообщение о начале завершения работы программы
     * */
    void finishing();

    /**
     * Сообщение о конце завершения работы программы
     */
    void finished();

    /**
     * Сообщение о неопознанной команде
     */
    void unknownCommand();

}
