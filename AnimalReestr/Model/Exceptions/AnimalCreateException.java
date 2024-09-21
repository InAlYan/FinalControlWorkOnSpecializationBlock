package Model.Exceptions;


public abstract class AnimalCreateException extends Exception {

    /**
     * Конструктор исключений возможных при создании животного
     * @param message сообщение об исключении при создании животного
     */
    public AnimalCreateException(String message) {
        super(message);
    }

}
