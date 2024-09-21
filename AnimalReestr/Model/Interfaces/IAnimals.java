package Model.Interfaces;

import Model.Domain.Animal;
import Model.Exceptions.EmptyAnimalsException;

import java.util.ArrayList;

public interface IAnimals {

    /**
     * Получить список животных типа ArrayList<Animal>
     * @return список животных типа ArrayList<Animal>
     */
    ArrayList<Animal> getAnimals();

    /**
     * Получить номер текущего животного типа int
     * @return номер текущего животного типа int
     */
    int getCurrentNumberAnimal() throws EmptyAnimalsException;

    /**
     * Получить текущее животное типа Animal
     * @return текущее животное типа Animal
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal getCurrentAnimal() throws EmptyAnimalsException;

    /**
     * Установить текущее животное по индексу currentNumberAnimal типа int (счет начиная c 1)
     * @param currentNumberAnimal индекс текущего животного currentNumberAnimal типа int
     * @return индекс текущего животного currentNumberAnimal типа int
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    int setCurrentNumberAnimal(int currentNumberAnimal) throws EmptyAnimalsException;

    /**
     * Установить текущий номер животного по кличке nickname типа String
     * @param nickname - кличка животного типа String
     * @return текущий номер животного типа int
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    int setCurrentNumberAnimal(String nickname) throws EmptyAnimalsException;

    /**
     * Возвращает первое найденное животное типа Animal по кличке
     * @param nickname кличка животного типа String
     * @return животное типа Animal или null, если животное по кличке не найдено
     */
    Animal animalByName(String nickname);

    /**
     * Установить следующее по списку животное текущим, при достижении конечного значения оно не меняется
     * @return следующее по списку животное типа Animal
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal nextCurrentAnimal() throws EmptyAnimalsException;

    /**
     * Установить предыдущее по списку животное текущим, при достижении начального значения оно не меняется
     * @return предыдущее по списку животное типа Animal
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal prevCurrentAnimal() throws EmptyAnimalsException;

    /**
     * Добавить животное типа Animal в список животных типа ArrayList<Animal>
     * @param animal животное типа Animal
     * @return животное типа Animal или null
     */
    Animal addAnimal(Animal animal);

    /**
     * Удалить животное animal типа Animals из реестра животных типа ArrayList<Animal> по индексу
     * @param index типа int индекс удаляемого животного
     * @return возвращает удаленное животное типа Animal, если удаления не произошло, то возвращает null
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal removeAnimal(int index) throws EmptyAnimalsException;

    /**
     * Удалить первое встреченное животное animal типа Animals из реестра животных типа ArrayList<Animal> по имени
     * @param nickname типа String имя удаляемого животного
     * @return возвращает удаленное животное типа Animal, если удаления не произошло, то возвращает null
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal removeAnimal(String nickname) throws EmptyAnimalsException;

    /**
     * Удалить животное animal типа Animal из коллекции animals типа ArrayList<Animal>
     * @param animal животное типа Animal
     * @return возвращает удаленное животное типа Animal, если удаления не произошло, то возвращает null
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal removeAnimal(Animal animal) throws EmptyAnimalsException;

    /**
     * Удалить животное animal типа Animal из коллекции animals типа ArrayList<Animal> по идентификатору id типа int
     * @param id идентификатор животного id типа int
     * @return возвращает удаленное животное типа Animal, если удаления не произошло, то возвращает null
     * @throws EmptyAnimalsException исключение при пустом списке животных
     */
    Animal removeAnimalByID(int id) throws EmptyAnimalsException;

}
