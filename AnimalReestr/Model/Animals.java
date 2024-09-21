package Model;

import Model.Domain.*;
import Model.Exceptions.EmptyAnimalsException;
import Model.Interfaces.IAnimals;

import java.util.ArrayList;
import java.util.Objects;


public class Animals implements IAnimals {

    /**
     * Список животных типа ArrayList<Animal>
     */
    private ArrayList<Animal> animals;

    /**
     * Номер текущего животного
     */
    private int currentNumberAnimal;

    /**
     * Получить список животных типа ArrayList<Animal>
     * @return список животных типа ArrayList<Animal>
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * Установить список животных типа ArrayList<Animal>
     * @param animals список животных типа ArrayList<Animal>
     */
    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    /**
     * Получить номер текущего животного типа int (счет начинается c 1)
     * @return номер текущего животного типа int
     * @throws EmptyAnimalsException список животных пуст
     */
    public int getCurrentNumberAnimal() throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        return this.currentNumberAnimal + 1; // Увеличиваем результат начинающийся с 0, чтобы соответствовал представлению человека, начинающимся с 1
    }

    /**
     * Получить текущее животное типа Animal
     * @return текущее животное типа Animal
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal getCurrentAnimal() throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        return animals.get(this.currentNumberAnimal);
    }

    /**
     * Конструктор
     */
    public Animals() {
        this.animals = new ArrayList<>();
        this.currentNumberAnimal = -1;
    }

    /**
     * Конструктор
     * @param animals список животных типа ArrayList<Animal>
     */
    public Animals(ArrayList<Animal> animals) {
        this.animals = animals;
        this.currentNumberAnimal = this.animals.size() - 1;
    }

    /**
     * Установить текущее животное по индексу currentNumberAnimal типа int (счет начинается c 1)
     * @param currentNumberAnimal индекс текущего животного currentNumberAnimal типа int
     * @return индекс текущего животного currentNumberAnimal типа int (счет начинается c 1)
     * @throws EmptyAnimalsException список животных пуст
     */
    public int setCurrentNumberAnimal(int currentNumberAnimal) throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        this.currentNumberAnimal = --currentNumberAnimal; // Уменьшаем внешний аргумент начинающийся с 1 чтобы соответствовал внутренним индексам в ArrayList, начинающимся с 0
        if (this.currentNumberAnimal < 0) this.currentNumberAnimal = 0;
        if (this.currentNumberAnimal >= this.animals.size()) this.currentNumberAnimal = this.animals.size() - 1;
        return this.currentNumberAnimal + 1;
    }

    /**
     * Установить текущий номер животного по кличке nickname типа String
     * @param nickname - кличка животного типа String
     * @return текущий номер животного типа int (счет начинается c 1)
     * @throws EmptyAnimalsException список животных пуст
     */
    public int setCurrentNumberAnimal(String nickname) throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        int index = animalNumberByName(nickname);
        if (index >= 0) this.currentNumberAnimal = index;
        return this.currentNumberAnimal + 1;
    }

    /**
     * Возвращает индекс первого найденного животного типа int по кличке
     * @param nickname кличка животного типа String
     * @return индекс первого найденного животного типа int или - 1, если животное по кличке не найдено
     */
    private int animalNumberByName(String nickname) {
        for (int i = 0; i < this.animals.size(); i++)
            if (Objects.equals(animals.get(i).getNickname().toLowerCase(), nickname.toLowerCase())) return i;
        return -1;
    }

    /**
     * Возвращает первое найденное животное типа Animal по кличке
     * @param nickname кличка животного типа String
     * @return животное типа Animal или null, если животное по кличке не найдено
     */
    public Animal animalByName(String nickname) {
        for (Animal animal: this.animals)
            if (Objects.equals(animal.getNickname().toLowerCase(), nickname.toLowerCase())) return animal;
        return null;
    }

    /**
     * Возвращает первое найденное животное типа Animal по идентификатору
     * @param id идентификатор животного типа id
     * @return животное типа Animal или null, если животное по идентификатору не найдено
     */
    public Animal animalById(int id) {
        for (Animal animal: this.animals)
            if (Objects.equals(animal.getAnimalId(), id)) return animal;
        return null;
    }

    /**
     * Установить следующее по списку животное текущим, при достижении конечного значения оно не меняется
     * @return следующее по списку животное типа Animal
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal nextCurrentAnimal() throws EmptyAnimalsException{
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        if (this.currentNumberAnimal == this.animals.size() - 1) return animals.get(this.currentNumberAnimal);
        else return animals.get(++this.currentNumberAnimal);
    }

    /**
     * Установить предыдущее по списку животное текущим, при достижении начального значения оно не меняется
     * @return предыдущее по списку животное типа Animal
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal prevCurrentAnimal() throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");
        if (this.currentNumberAnimal == 0) return animals.get(this.currentNumberAnimal);
        else return animals.get(--this.currentNumberAnimal);
    }

    /**
     * Добавить животное типа Animal в список животных типа ArrayList<Animal>
     * @param animal животное типа Animal
     * @return животное типа Animal или null
     */
    public Animal addAnimal(Animal animal) {
        if (!animals.contains(animal))
            if (animals.add(animal)) {
                this.currentNumberAnimal = this.animals.size() - 1;
                return animal;
            }
        return null;
    }

    /**
     * Удалить животное animal типа Animals из реестра животных типа ArrayList<Animal> по индексу (счет идет с 1)
     * @param index типа int индекс удаляемого животного
     * @return возвращает удаленное животное типа Animal, если такого животного не обнаружено, то возвращает null
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal removeAnimal(int index) throws EmptyAnimalsException{
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");

        index--; // Внешний аргумент начинается с 1, а индексация ArrayList c 0
        if (index >= 0 && index < this.animals.size()) { // Переданный индекс в заданном диапазоне
            if (this.animals.size() == 1) { // Список животных из одного элемента
                this.currentNumberAnimal = -1; // Список животных станет пустым
            } else { // Список животных более чем из одного элемента
                if (index == this.animals.size() - 1)
                    this.currentNumberAnimal = index - 1; // Текущий элемент последний элемент
                else
                    this.currentNumberAnimal = index; // Текущий элемент последующий от удаляемого
            }
            return this.animals.remove(index);
        }
        return null;
    }

    /**
     * Удалить первое встреченное животное animal типа Animals из реестра животных типа ArrayList<Animal> по имени
     * @param nickname типа String имя удаляемого животного
     * @return возвращает удаленное животное типа Animal, если такого животного не обнаружено, то возвращает null
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal removeAnimal(String nickname) throws EmptyAnimalsException{
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");

        Animal animal = animalByName(nickname);
        if (animal != null) {
            if (this.animals.size() == 1) {  // Список животных из одного элемента
                this.currentNumberAnimal = -1;
            } else { // Список животных более чем из одного элемента
                int indexAnimal = animals.indexOf(animal);
                if (indexAnimal == this.animals.size() - 1)
                    this.currentNumberAnimal = indexAnimal - 1; // Текущий элемент последний элемент
                else
                    this.currentNumberAnimal = indexAnimal; // Текущий элемент последующий от удаляемого
            }
            if (this.animals.remove(animal)) return animal;
        }
        return null;
    }

    /**
     * Удалить животное animal типа Animal из коллекции animals типа ArrayList<Animal>
     * @param animal животное типа Animal
     * @return возвращает удаленное животное типа Animal, если такого животного не обнаружено, то возвращает null
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal removeAnimal(Animal animal) throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст..."); // Здесь необязательно
        int indexAnimal = animals.indexOf(animal);

        if (indexAnimal != -1) { // Такое животное есть в списке
            if (this.animals.size() == 1) {  // Список животных из одного элемента
                this.currentNumberAnimal = -1;
            } else { // Список животных более чем из одного элемента
                if (indexAnimal == this.animals.size() - 1)
                    this.currentNumberAnimal = indexAnimal - 1; // Текущий элемент последний элемент
                else
                    this.currentNumberAnimal = indexAnimal; // Текущий элемент последующий от удаляемого
            }
            if (this.animals.remove(animal)) return animal;
        }
        return null;
    }

    /**
     * Удалить животное animal типа Animal из коллекции animals типа ArrayList<Animal> по идентификатору id типа int
     * @param id идентификатор животного id типа int
     * @return возвращает удаленное животное типа Animal, если такого животного с таким идентификатором не обнаружено, то возвращает null
     * @throws EmptyAnimalsException список животных пуст
     */
    public Animal removeAnimalByID(int id) throws EmptyAnimalsException {
        if (this.animals.isEmpty()) throw new EmptyAnimalsException("Ошибка: cписок животных пуст...");

        Animal animal = animalById(id);
        if (animal != null) {
            if (this.animals.size() == 1) {  // Список животных из одного элемента
                this.currentNumberAnimal = -1;
            } else { // Список животных более чем из одного элемента
                int indexAnimal = animals.indexOf(animal);
                if (indexAnimal == 0)
                    this.currentNumberAnimal = 0; // Текущий элемент начальный элемент
                else
                    this.currentNumberAnimal--; // Текущий элемент предыдущий от удаляемого
            }
            if (this.animals.remove(animal)) return animal;
        }
        return null;
    }

    /**
     * Переопределенное представление объекта Animals
     * @return String в виде списка животных
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Животные:\n");
        if (this.animals.isEmpty()) return sb.toString();
        else sb.append("========== НАЧАЛО СПИСКА ЖИВОТНЫХ ==========\n");
        for (int i = 0; i < this.animals.size(); i++)
            sb.append((i + 1)).append(" ").append(this.animals.get(i)).append("\n-------------------------------------------\n");
        sb.append("========== КОНЕЦ СПИСКА ЖИВОТНЫХ ==========");
        return sb.toString();
    }
}
