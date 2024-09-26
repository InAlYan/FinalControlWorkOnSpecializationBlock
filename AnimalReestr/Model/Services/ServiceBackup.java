package Model.Services;

import Model.Domain.*;
import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;
import Model.Interfaces.IAnimals;
import Model.Interfaces.ICommands;
import Model.Interfaces.IServiceBackup;

import java.io.*;
import java.text.ParseException;
import java.util.Date;

public class ServiceBackup implements IServiceBackup {

    /**
     * Список животных
     */
    private IAnimals animals;

    /**
     * Список команд
     */
    private ICommands commands;

    /**
     * Конструктор
     * @param animals список животных типа IAnimals
     * @param commands список команд типа Commands
     */
    public ServiceBackup(IAnimals animals, ICommands commands) {
        this.animals = animals;
        this.commands = commands;
    }

    /**
     * Сохраняет список животных в файл в формате представления объектов
     * @param fileName наименование файла для сохранения
     */
    public void saveAnimals(String fileName) {
        try (FileWriter fr = new FileWriter(fileName)) {
            StringBuilder sb =new StringBuilder();
            for (Animal animal : animals.getAnimals()) {
                String animalKind = "";
                if (animal.getClass() == Dog.class) animalKind = "DOG";
                if (animal.getClass() == Cat.class) animalKind = "CAT";
                if (animal.getClass() == Hamster.class) animalKind = "HAMSTER";
                if (animal.getClass() == Horse.class) animalKind = "HORSE";
                if (animal.getClass() == Camel.class) animalKind = "CAMEL";
                if (animal.getClass() == Donkey.class) animalKind = "DONKEY";
                sb.append(animalKind).append("\n").append(animal).append(";\n");
            }
            fr.write(sb.toString());
            fr.flush();
        } catch (IOException e) {
            System.out.println("Не удалось сохранить файл...");
        }
        System.out.println("Данные сохранены в " + fileName);
    }

    /**
     * Загружает список животных из файла в список животных типа ArrayList<Animal>
     * @param fileName наименование файла для загрузки
     * @return maxId типа int максимальный идентификатор объекта типа Animal из списка загруженных
     */
    public int loadAnimals(String fileName) {
        int maxId = 0;
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            String newLine;

            while (br.ready()) {

                int ID;
                String strAnimalKind, strNickname, strAnimalCommands;
                Date dateBirthday = null;
                int AnimalTypeSpecificProperty, SpecificProperty;

                StringBuilder sb = new StringBuilder();

                // Читаем построчно данные об объекте до символа ";" или до конца файла и собираем строку объекта
                do {
                    newLine = br.readLine();
                    sb.append(newLine).append('\n');
                } while (br.ready() && !newLine.contains(";"));                

                // Пытаемся парсить данные об объектах представляющих животных
                try {
                    String[] strArr = sb.toString().split("\n"); // Разбираем полученную строку по символу переноса строки
                    strAnimalKind = strArr[0].toUpperCase(); // 0 строку из массива строк - вид животного
                    String[] strNicknameBirthdayArr = strArr[1].split(","); // Разбираем 1 строку из массива с id, кличкой и днем рождения по символу запятая

                    String[] strIDArr = strNicknameBirthdayArr[0].split(": "); // Разбираем 0 часть 1 строки из массива с id, кличкой по символу пробел
                    String strID = strIDArr[strIDArr.length - 1]; // ID

                    String[] strNicknameArr = strNicknameBirthdayArr[1].split(": "); // Разбираем 1 часть 1 строки из массива с id, кличкой по символу пробел
                    strNickname = strNicknameArr[strNicknameArr.length - 1].replaceAll("_", " "); // Кличка

                    String[] strBirthdayArr = strNicknameBirthdayArr[2].split(": "); // Разбираем 2 часть 1 строки из массива с датой рождения по символу пробел
                    String strBirthday = strBirthdayArr[strBirthdayArr.length - 1];  // День рождения

                    StringBuilder commandStringBuilder = new StringBuilder();
                    if (strArr.length > 4) { // Если команды у животного указаны
                        for (int i = 3; i < strArr.length - 2 ; i++) { // Вычленяем команды из строк (с 3 с начала по предпоследнюю), для животных у которых они есть
                            String[] strAnimalCommandArr = strArr[i].split(" ");
                            String strAnimalCommand = strAnimalCommandArr[strAnimalCommandArr.length - 1]; // Текущая команда
                            commandStringBuilder.append(strAnimalCommand).append(";");
                        }
                    }
                    strAnimalCommands = commandStringBuilder.toString();

                    // Разбираем предпоследнюю строку из массива с типом животного и специфическим полем типа животного по символу запятая
                    String[] strAnimalTypeSpecificPropertyArr = strArr[strArr.length - 2].split(" ");
                    String strAnimalTypeSpecificProperty = strAnimalTypeSpecificPropertyArr[strAnimalTypeSpecificPropertyArr.length - 1]; //strAnimalTypeSpecificProperty

                    // Разбираем последнюю строку из массива с видом животного и специфическим полем вида животного по символу запятая
                    String[] strSpecificPropertyArr = strArr[strArr.length - 1].split(" ");
                    String strSpecificProperty = strSpecificPropertyArr[strSpecificPropertyArr.length - 1].replace(";", ""); // strSpecificProperty

                    try {
                        dateBirthday = Animal.getDateFormat().parse(strBirthday);
                    } catch (ParseException ignored) {
                    }

                    ID = Integer.parseInt(strID);

                    if (ID > maxId) maxId = ID; // Запоминаем текущий максимальный идентификатор из файла

                    AnimalTypeSpecificProperty = Integer.parseInt(strAnimalTypeSpecificProperty);
                    SpecificProperty = Integer.parseInt(strSpecificProperty);

                } catch (Exception e) {
                    System.out.println("Не удалось прочитать данные из файла! Структура файла частично повреждена! " + fileName);
                    continue;
                }

                // Собираем объект типа Animal
                Animal animal = null;
                try {
                        switch (strAnimalKind) {
                        case "DOG":
                            animal = Dog.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        case "CAT":
                            animal = Cat.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        case "HAMSTER":
                            animal = Hamster.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        case "HORSE":
                            animal = Horse.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        case "CAMEL":
                            animal = Camel.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        case "DONKEY":
                            animal = Donkey.create(ID, strNickname, dateBirthday, AnimalTypeSpecificProperty, SpecificProperty);
                            break;
                        default:
                            animal = null;
                            break;
                    };
                } catch (AnimalNickNameException e) {
                    System.out.println(strAnimalKind + " => " + e.getMessage() + e.getNickname() + " => не создано");
                } catch (AnimalBirthdayException e) {
                    System.out.println(strAnimalKind + " => " + e.getMessage() + e.getBirthday() + " => не создано");
                } catch (AnimalSpecificTypeException e) {
                    System.out.println(strAnimalKind + " => " + e.getMessage() + e.getProperty() + " => не создано");
                } catch (AnimalSimpleDateFormatException e) {
                    System.out.println(strAnimalKind + " => " + e.getMessage() + " => не создано");
                }

                // Добавляем команды к объекту типа Animal, если есть
                if (!strAnimalCommands.isEmpty()) {
                    String[] strAnimalCommandsArr = strAnimalCommands.split(";");
                    for (String commandName :strAnimalCommandsArr) {
                        Command command = commands.getCommand(commandName);
                        // Есть ли в списке всех команд наша команда
                        if (command == null) command = commands.addCommand(commandName); // Если по неведомой причине команды в списке всех команд нет, добавляем команду в список всех команд
                        if (command != null && animal != null) animal.learnCommand(command); // Добавляем команду в список команд животного
                    }
                }
                if (animal != null) animals.addAnimal(animal); // Добавляем в список animals
            }
            System.out.println("Данные из файла " + fileName + " подгружены...");
            return maxId;
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось обнаружить файл с данными: " + fileName);
        } catch (IOException e) {
            System.out.println("Не удалось прочитать данные из файла: " + fileName);
        }
        return maxId;
    }

}
