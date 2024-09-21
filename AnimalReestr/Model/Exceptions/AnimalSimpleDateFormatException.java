package Model.Exceptions;

public class AnimalSimpleDateFormatException extends Exception{

    /**
     * Конструктор исключения не задан формат типа SimpleDateFormat преобразования данных из строки в Date
     * @param message сообщение об исключении
     */
    public AnimalSimpleDateFormatException(String message) {
        super(message);
    }

}