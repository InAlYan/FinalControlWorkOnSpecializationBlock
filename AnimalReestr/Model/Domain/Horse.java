package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Horse extends PackAnimal {

    /**
     * Количество побед в гонках
     */
    protected int winRaceCount;

    /**
     * Возвращает количество побед в гонках (специфическое свойство лошадей) типа int
     * @return количество побед в гонках (специфическое свойство лошадей) типа int
     */
    public int getWinRaceCount() {
        return winRaceCount;
    }

    /**
     * Устанавливает количество побед в гонках (специфическое свойство лошадей) типа int
     * @param winRaceCount количество побед в гонках (специфическое свойство лошадей) типа int
     */
    public void setWinRaceCount(int winRaceCount) {
        this.winRaceCount = winRaceCount;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param loadCapacity - грузоподъемность типа int
     * @param winRaceCount - количество побед в гонках типа int
     */
    private Horse(int animalId, String nickname, Date birthday, int loadCapacity, int winRaceCount) {
        super(animalId, nickname, birthday, loadCapacity);
        this.winRaceCount = winRaceCount;
    }

    /**
     * Фабричный метод создания экземпляра класса Horse
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка лошади
     * @param birthday - String день рождения лошади
     * @param loadCapacity - int грузоподъемность лошади в кг (0-1000 кг)
     * @param winRaceCount - int количество побед лошади в гонках (0-1000 раз)
     * @return экземпляр класса Horse
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Horse create(int animalId, String nickname, String birthday, int loadCapacity, int winRaceCount)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Horse.create(animalId, nickname, birthdayDate, loadCapacity, winRaceCount);
    }

    /**
     * Фабричный метод создания экземпляра класса Horse
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка лошади
     * @param birthday - Date день рождения лошади
     * @param loadCapacity - int грузоподъемность лошади в кг (0-1000 кг)
     * @param winRaceCount - int количество побед лошади в гонках (0-1000 раз)
     * @return экземпляр класса Horse
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Horse create(int animalId, String nickname, Date birthday, int loadCapacity, int winRaceCount)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {
        if (nickname == null || nickname.length() < 2  || nickname.length() > 50  || !nickname.toLowerCase().matches ("[a-zа-я ]+"))
            throw new AnimalNickNameException("Некорректная кличка: ", nickname);

        Calendar calendar = new GregorianCalendar();
        Date now = calendar.getTime(); // Сейчас (дата)
        calendar.add(Calendar.YEAR, - 100); // 100 лет назад (дата)

        // Корректность даты
        if (birthday == null)
            throw new AnimalBirthdayException("Некорректная дата рождения: ", "не задана");
        //Будущая дата
        if (birthday.after(now))
            throw new AnimalBirthdayException("Лошадь еще не родилась: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Лошади больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что грузоподъемность число от 0 до 1000
        if (loadCapacity < 0 || loadCapacity > 1000)
            throw new AnimalSpecificTypeException("Грузоподъемность должна быть от 0 до 1000 кг : ", loadCapacity);

        if (winRaceCount < 0 || winRaceCount > 1000)
            throw new AnimalSpecificTypeException("Количество побед должно быть от 0 до 1000 раз: ", winRaceCount);

        return new Horse(animalId, nickname, birthday, loadCapacity, winRaceCount);
    }

    /**
     * Так звучит лошадь
     * @return звук в текстовом формате, издаваемый лошадью
     */
    @Override
    public String response() {
        return super.response() + "иго-го";
    }

    /**
     * Краткое представление объекта лошадь
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Лошадь " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Horse
     * @return String c описание объекта типа Horse
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: лошадь, количество побед в гонках: " + this.winRaceCount;
    }
    
}
