package Model.Domain;

import java.util.Date;

public abstract class PackAnimal extends Animal {
    
    /**
     * Грузоподъемность
     */
    protected int loadCapacity;

    /**
     * Возвращает грузоподъемность (специфическое свойство вьючных животных) типа int
     * @return грузоподъемность (специфическое свойство вьючных животных) типа int
     */
    public int getLoadCapacity() {
        return loadCapacity;
    }

    /**
     * Устанавливает грузоподъемность (специфическое свойство вьючных животных) типа int
     * @param loadCapacity грузоподъемность (специфическое свойство вьючных животных) типа int
     */
    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param loadCapacity - грузоподъемность типа int
     */
    public PackAnimal(int animalId, String nickname, Date birthday, int loadCapacity) {
        super(animalId, nickname, birthday);
        this.loadCapacity = loadCapacity;
    }

    /**
     * Переопределенное представление объекта PackAnimal
     * @return String c описание объекта типа PackAnimal
     */
    @Override
    public String toString() {
        return super.toString() + "тип: вьючное, грузоподъемность: " + this.loadCapacity;
    }
    
}