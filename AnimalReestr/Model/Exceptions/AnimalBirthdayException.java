package Model.Exceptions;

public class AnimalBirthdayException extends AnimalCreateException {

    /**
     * День рождения в строковом формате
     */
    private String birthday;

    /**
     * Возвращает день рождения в строковом формате
     * @return день рождения в строковом формате
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Конструктор исключения при работе с датой рождения животного
     * @param message сообщение об исключении при вводе даты рождения животного (будущая дата, дата более 100 лет назад, некорректная дата)
     * @param birthday день рождения в строковом формате
     */
    public AnimalBirthdayException(String message, String birthday) {
        super(message);
        this.birthday = birthday;
    }
}
