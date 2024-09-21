package Model.Exceptions;


public class AnimalNickNameException extends AnimalCreateException{

    /**
     * Кличка животного
     */
    private String nickname;

    /**
     * Возвращает кличку в строковом формате
     * @return кличку в строковом формате
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Конструктор исключения при работе с кличкой животного
     * @param message сообщение об исключении при вводе клички животного (меньше 2 символов, больше 50 символов, не (алфавитные символы рус или англ или пробел))
     * @param nickname кличка животного
     */
    public AnimalNickNameException(String message, String nickname) {
        super(message);
        this.nickname = nickname;
    }

}
