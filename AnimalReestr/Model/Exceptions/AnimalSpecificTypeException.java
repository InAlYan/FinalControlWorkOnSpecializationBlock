package Model.Exceptions;

public class AnimalSpecificTypeException extends AnimalCreateException{

    /**
     * Значение специфического свойства животного (например дружелюбность в %)
     */
    public int property;

    /**
     * Возвращает значение специфического свойства животного (например дружелюбность в %)
     * @return значение специфического свойства животного (например дружелюбность в %)
     */
    public int getProperty() {
        return property;
    }

    /**
     * Конструктор исключения при работе со специфическими свойствами
     * @param message сообщение об исключении при вводе клички животного (выход за пределы диапазона например менее0 или более 100%)
     * @param property значение специфического свойства животного (например дружелюбность в %)
     */
    public AnimalSpecificTypeException(String message, int property) {
        super(message);
        this.property = property;
    }

}
