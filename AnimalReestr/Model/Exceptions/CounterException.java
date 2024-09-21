package Model.Exceptions;

public class CounterException extends Exception{

    /**
     * Конструктор исключения при работе со счетчиком
     * @param message сообщение об исключении при работе со счетчиком
     */
    public CounterException(String message) {
        super(message);
    }

}
