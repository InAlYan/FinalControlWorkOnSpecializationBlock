package Model.Domain;


import java.util.Date;

public abstract class Pet extends Animal {

    /**
     * Дружелюбность
     */
    protected int friendliness;

    /**
     * Возвращает дружелюбность (специфическое свойство домашних животных) типа int
     * @return дружелюбность (специфическое свойство домашних животных) типа int
     */
    public int getFriendliness() {
        return friendliness;
    }

    /**
     * Устанавливает дружелюбность (специфическое свойство домашних животных) типа int
     * @param friendliness дружелюбность (специфическое свойство домашних животных) типа int
     */
    public void setFriendliness(int friendliness) {
        this.friendliness = friendliness;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param friendliness - дружелюбность типа int
     */
    public Pet(int animalId, String nickname, Date birthday, int friendliness){
        super(animalId, nickname, birthday);
        this.friendliness = friendliness;
    }

    /**
     * Переопределенное представление объекта Pet
     * @return String c описание объекта типа Pet
     */
    @Override
    public String toString() {
        return super.toString() +  "тип: домашнее, дружелюбность: " + this.friendliness;
    }

}
