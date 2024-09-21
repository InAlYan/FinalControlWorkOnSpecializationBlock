package Model;

import Model.Exceptions.CounterException;

public class Counter implements AutoCloseable {

    /**
     * Знаяение счетчика типа int
     */
    private int counter;

    /**
     * Признак того, что счетчик продолжает работу
     */
    private boolean isOpen;

    /**
     * Получить значение счетчика типа int
     * @return значение счетчика типа int
     */
    public int getCounter() {
        return counter;
    }


    /**
     * Конструктор
     * @param counter начальное значение счетчика типа int
     */
    public Counter(int counter) {
        this.counter = counter;
        this.isOpen = true;
    }

    /**
     * Увеличить значение счетчика на 1
     * @return значение счетчика, увеличенное на 1
     * @throws CounterException счетчик отработан и закрыт
     */
    public int add() throws CounterException {
        if (isOpen)
            return ++counter;
        else
            throw new CounterException("Счетчик закрыт");
    }

    /**
     * Закрытие счетчика
     * @throws Exception неизвестное возможное исключение
     */
    @Override
    public void close() throws Exception {
        this.isOpen = false;
    }
}
