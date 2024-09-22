package Controller;

import Model.*;
import Model.Domain.*;
import Model.Exceptions.*;
import Model.Interfaces.IAnimals;
import Model.Interfaces.ICommands;
import Model.Interfaces.IServiceBackup;
import Model.Services.ServiceBackup;
import View.Interfaces.IAnimalView;

import java.util.Arrays;
import java.util.Scanner;

public class AnimalController {

    /**
     * Подключаемый view типа IAnimalView
     */
    private IAnimalView view;

    /**
     * Подключаемая модель данных animals типа IAnimals
     */
    private IAnimals animals;

    /**
     * Список всех команд
     */
    private ICommands commands;

    /**
     * Получить view типа IAnimalView
     * @return view типа IAnimalView
     */
    public IAnimalView getView() {
        return view;
    }

    /**
     * Установить view типа IAnimalView
     * @param view типа IAnimalView
     */
    public void setView(IAnimalView view) {
        this.view = view;
    }

    /**
     * Получить модель данных animals типа IAnimals
     * @return модель данных animals типа IAnimals
     */
    public IAnimals getAnimals() {
        return animals;
    }

    /**
     * Установить модель данных animals типа IAnimals
     * @param animals модель данных animals типа IAnimals
     */
    public void setAnimals(IAnimals animals) {
        this.animals = animals;
    }

    /**
     * Получить список всех команд типа ICommands
     * @return список всех команд типа ICommands
     */
    public ICommands getCommands() {
        return commands;
    }

    /**
     * Установить список всех команд типа ICommands
     * @param commands список всех команд типа ICommands
     */
    public void setCommands(ICommands commands) {
        this.commands = commands;
    }

    /**
     * Конструктор
     * @param view - подключаемый view типа IAnimalView
     * @param animals - подключаемая модель данных animals типа IAnimals
     * @param commands - список всех команд
     */
    public AnimalController(IAnimalView view, IAnimals animals, ICommands commands) {
        this.view = view;
        this.animals = animals;
        this.commands = commands;
    }

    /**
     * Запуск контроллера работы реестр животных на исполнение
     */
    public void start(){

        IServiceBackup serviceBackup = new ServiceBackup(animals, commands);

        int currentID = serviceBackup.loadAnimals("animal_registry.txt");
        System.out.println("<<<<<<<<<<<<<<<<<<<<< РЕЕСТР ЖИВОТНЫХ >>>>>>>>>>>>>>>>>>>>>");

        Scanner scanner = new Scanner(System.in);
        String inpCommand;

        do {
            // Показать текущее животное
            try {
                view.selectedAnimal(animals.getCurrentNumberAnimal(), animals.getCurrentAnimal());
            } catch (EmptyAnimalsException e) {
                view.selectedAnimalError();
            }

            // Показать меню
            view.showMenu();

            System.out.print("Введите команду: ");
            inpCommand = scanner.nextLine();

            // Обработка введенных команд
            switch (inpCommand) {
                case "aa": // aa (all animals) - Показать всех животных
                    view.showAllAnimals(animals);
                    break;

                case "as": // as (select animal) - Выбрать животное
                    String selectedAnimal = view.prompt("Выберите животное по номеру или имени: ");
                    Integer indToSelect = null;
                    try {
                        indToSelect = Integer.parseInt(selectedAnimal);
                    } catch (NumberFormatException ignored) {
                    }
                    if (indToSelect != null) {
                        try {
                            view.selectAnimal(animals.setCurrentNumberAnimal(indToSelect), animals.getCurrentAnimal());
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                            view.selectAnimalError();
                        }
                    } else {
                        try {
                            view.selectAnimal(animals.setCurrentNumberAnimal(selectedAnimal), animals.getCurrentAnimal());
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                            view.selectAnimalError();
                        }
                    }
                    break;

                case "na": // na (next animal) - Следующее животное
                    try {
                        animals.nextCurrentAnimal();
                        view.nextAnimal(animals.getCurrentNumberAnimal(), animals.getCurrentAnimal());
                    } catch (EmptyAnimalsException e) {
                        System.out.println(e.getMessage());
                        view.nextAnimalError();
                    }
                    break;

                case "pa": // pa (previous animal) - Предыдущее животное
                    try {
                        animals.prevCurrentAnimal();
                        view.previousAnimal(animals.getCurrentNumberAnimal(), animals.getCurrentAnimal());
                    } catch (EmptyAnimalsException e) {
                        System.out.println(e.getMessage());
                        view.previousAnimalError();
                    }
                    break;

                case "an": // an (animal new) - Новое животное
                    AnimalKind animalKind = view.strToAnimalKind(String.format("Вид животного %s или нажмите ввод для отказа: ", Arrays.toString(AnimalKind.values())) );
                    if (animalKind == null) break;

                    String nickname = view.prompt("Имя животного: ");
                    if (nickname.isBlank()) break;

                    String birthday = view.prompt("День рождения животного в формате yyyy-mm-dd: ");
                    if (birthday.isBlank()) break;

                    int specificPetAnimalPackProperty, specificAnimalProperty;
                    if (animalKind == AnimalKind.DOG || animalKind == AnimalKind.CAT || animalKind == AnimalKind.HAMSTER)
                        specificPetAnimalPackProperty = view.strToIntForAnimalsCreate("Дружелюбность животного (0-100) или нажмите ввод (50% дружелюбность): ");
                    else
                        specificPetAnimalPackProperty = view.strToIntForAnimalsCreate("Грузоподъемность животного (кг) или нажмите ввод (50кг грузоподъемность): ");

                    Animal animal = null; // Создание класса нужного типа в соответствии с ENUM
                    try (Counter counter = new Counter(currentID)) {
                        currentID = counter.add();
                         switch (animalKind) {
                            case DOG: {
                                specificAnimalProperty = view.strToIntForAnimalsCreate("Агрессивность к чужим (0-100) или нажмите ввод (50% агрессивность): ");
                                animal = Dog.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                break;
                            }
                             case CAT:{
                                 specificAnimalProperty = view.strToIntForAnimalsCreate("Cонливость (0-100) или нажмите ввод (50% сонливость): ");
                                 animal = Cat.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                 break;
                             }
                             case HAMSTER: {
                                 specificAnimalProperty = view.strToIntForAnimalsCreate("Активность (0-100) или нажмите ввод (50% активность): ");
                                 animal = Hamster.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                 break;
                             }
                             case HORSE: {
                                 specificAnimalProperty = view.strToIntForAnimalsCreate("Количество побед (раз) на скачках или нажмите ввод (50 побед): ");
                                 animal = Horse.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                 break;
                             }
                             case CAMEL: {
                                 specificAnimalProperty = view.strToIntForAnimalsCreate("Потребление воды (л) или нажмите ввод (50л): ");
                                 animal = Camel.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                 break;
                             }
                             case DONKEY: {
                                 specificAnimalProperty = view.strToIntForAnimalsCreate("Упрямство (0-100) или нажмите ввод (50% упрямство): ");
                                 animal = Donkey.create(currentID, nickname, birthday, specificPetAnimalPackProperty, specificAnimalProperty);
                                 break;
                             }
                        }

                        if (animal != null) view.addAnimal(animals.addAnimal(animal));// Добавление созданного объекта в экземпляр класса Animals //

                    } catch (AnimalNickNameException e) {
                        view.addAnimalError();
                        System.out.printf( e.getMessage() + e.getNickname());
                    } catch (AnimalBirthdayException e) {
                        view.addAnimalError();
                        System.out.println(e.getMessage() + e.getBirthday());
                    } catch (AnimalSpecificTypeException e) {
                        view.addAnimalError();
                        System.out.println(e.getMessage() + e.getProperty());
                    } catch (CounterException e) {
                        view.addAnimalError();
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        view.addAnimalError();
                        System.out.println(e.getMessage());
                    }
                    break;

                case "ad": // ad (animal delete) - Удалить животное
                    String deletedAnimal = view.prompt("Имя или номер животного к удалению: ");
                    Integer indToDelete = null;
                    try { // Пробуем преобразовать ввод пользователя к номеру или имени животного
                        indToDelete = Integer.parseInt(deletedAnimal);
                    } catch (NumberFormatException ignored) {
                    }

                    if (indToDelete != null) // Если удалось преобразовать пользовательский ввод к индексу животного в списке животных
                        try {
                            view.removeAnimal(animals.removeAnimal(indToDelete)); // Пытаемся удалить животное по индексу в списке животных и сообщаем о результатах
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                            view.removeAnimalError();
                        }
                    else  // Если пользовательский ввод - это кличка животного
                        try {
                            view.removeAnimal(animals.removeAnimal(deletedAnimal)); // Пытаемся удалить животное по кличке и сообщаем о результатах
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                            view.removeAnimalError();
                        }
                    break;

                case "ac": // ac (animal commands) - Список команд животного
                    try {
                        view.showCommands(this.animals.getCurrentAnimal());
                    } catch (EmptyAnimalsException e) {
                        System.out.println(e.getMessage());
                        view.showCommandsError();
                    }
                    break;

                case "al": // al (animal learn) - Изучить новую команду животным
                    String learnedCommand = view.prompt("Название или номер команды к изучению животным из списка всех команд: ");
                    Command addedCommand;
                    try { // Пробуем преобразовать ввод пользователя к номеру команды или имени команды и ищем ее в общем списке команд
                        int indToLearn = Integer.parseInt(learnedCommand);
                        addedCommand = commands.getCommand(indToLearn);
                    } catch (NumberFormatException ex) {
                        addedCommand = commands.getCommand(learnedCommand);
                    }

                    try { // Пробуем добавить ее к списку команд текущего животного
                        if (addedCommand == null) {// Если такая команда не найдена в общем списке команд
                            view.notExistingInAllCommands(learnedCommand);
                            view.learnCommandError();
                        }
                        view.learnCommand(animals.getCurrentAnimal(), animals.getCurrentAnimal().learnCommand(addedCommand));
                    } catch (EmptyAnimalsException e) {
                        System.out.println(e.getMessage() + "\n");
                    }

                    break;

                case "af": // af (animal forget) - Забыть команду животным
                    String forgettableCommand = view.prompt("Название или номер команды к удалению животным из списка команд животного: ");
                    Command forgottenCommand = null;
                    Integer indToForget = null;
                    try { // Пробуем преобразовать ввод пользователя к номеру команды или имени команды
                         indToForget = Integer.parseInt(forgettableCommand);
                    } catch (NumberFormatException ignored) {}

                    if (indToForget != null) // Если удалось преобразовать пользовательский ввод к индексу команды
                        try {
                            forgottenCommand = animals.getCurrentAnimal().getCommand(indToForget); // Пробуем найти команду по индексу в списке команд животного
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                        }
                    else // Если пользовательский ввод - это имя команды
                        try {
                            forgottenCommand = animals.getCurrentAnimal().getCommand(forgettableCommand); // Пробуем найти команду по имени в списке команд животного
                        } catch (EmptyAnimalsException e) {
                            System.out.println(e.getMessage());
                        }

                    try {
                        if (forgottenCommand == null) {// Если такая команда у животного не найдена
                            view.notExistingInAnimalCommands(forgettableCommand);
                            view.forgetCommandError();
                        }
                        view.forgetCommand(animals.getCurrentAnimal(), animals.getCurrentAnimal().forgetCommand(forgottenCommand)); // Удаляем команду и отображаем удаление
                    } catch (EmptyAnimalsException ignored) {}
                    break;

                case "ca": // ca (command all) - Список всех команд
                    view.showAllCommands(commands);
                    break;

                case "cn": // cn (command new) - Добавить команду в список всех команд
                    view.addCommand(commands.addCommand(view.prompt("Название новой команды к добавлению в список всех команд: ")));
                    break;

                case "cd": // cd (command delete) - Удалить команду из списка всех команд
                    String deletedCommand = view.prompt("Название или номер команды к удалению из списка всех команд: ");
                    try {
                        int indToDeleteCom = Integer.parseInt(deletedCommand);
                        view.removeCommand(commands.removeCommand(indToDeleteCom));
                    } catch (NumberFormatException ex) {
                        view.removeCommand(commands.removeCommand(deletedCommand));
                    }
                    break;

                case "q": // q (quit) - Выход
                    view.finishing();
                    break;

                default: // Неизвестная команда
                    view.unknownCommand();
                    break;

            }
        } while (!inpCommand.equals("q"));

        scanner.close();

        serviceBackup.saveAnimals("animal_registry.txt");
        view.finished();
    }

}