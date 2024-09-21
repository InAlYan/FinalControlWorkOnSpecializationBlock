package Model.Exceptions;

public class EmptyAnimalsException extends Exception{

    /**
     * Конструктор исключений при попытках работать со списком животных
     * @param message сообщение об исключении при работе со списком животных
     */
    public EmptyAnimalsException(String message) {
        super(message);
    }

}
