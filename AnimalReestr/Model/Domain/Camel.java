package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Camel extends PackAnimal {

    /**
     * Потребление воды
     */
    protected int waterConsumption;

    /**
     * Возвращает потребление воды (специфическое свойство верблюдов) типа int
     * @return потребление воды (специфическое свойство верблюдов) типа int
     */
    public int getWaterConsumption() {
        return waterConsumption;
    }

    /**
     * Устанавливает потребление воды (специфическое свойство верблюдов) типа int
     * @param waterConsumption потребление воды (специфическое свойство верблюдов) типа int
     */
    public void setWaterConsumption(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param loadCapacity - грузоподъемность типа int
     * @param waterConsumption - потребление воды типа int
     */
    private Camel(int animalId, String nickname, Date birthday, int loadCapacity, int waterConsumption) {
        super(animalId, nickname, birthday, loadCapacity);
        this.waterConsumption = waterConsumption;
    }

    /**
     * Фабричный метод создания экземпляра класса Camel
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка верблюда
     * @param birthday - String день рождения верблюда
     * @param loadCapacity - int грузоподъемность верблюда в кг (0-1000 кг)
     * @param waterConsumption - int водопотребление верблюда (0-1000 л)
     * @return экземпляр класса Camel
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Camel create(int animalId, String nickname, String birthday, int loadCapacity, int waterConsumption)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Camel.create(animalId, nickname, birthdayDate, loadCapacity, waterConsumption);
    }

    /**
     * Фабричный метод создания экземпляра класса Camel
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка верблюда
     * @param birthday - Date день рождения верблюда
     * @param loadCapacity - int грузоподъемность верблюда в кг (0-1000 кг)
     * @param waterConsumption - int водопотребление верблюда (0-1000 л)
     * @return экземпляр класса Camel
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Camel create(int animalId, String nickname, Date birthday, int loadCapacity, int waterConsumption)
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
            throw new AnimalBirthdayException("Верблюд еще не родился: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Верблюду больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что грузоподъемность число от 0 до 1000
        if (loadCapacity < 0 || loadCapacity > 1000)
            throw new AnimalSpecificTypeException("Грузоподъемность должна быть от 0 до 1000 кг : ", loadCapacity);

        if (waterConsumption < 0 || waterConsumption > 1000)
            throw new AnimalSpecificTypeException("Водопотребление должно быть от 0 до 1000 л: ", waterConsumption);

        return new Camel(animalId, nickname, birthday, loadCapacity, waterConsumption);
    }

    /**
     * Так звучит верблюд
     * @return звук в текстовом формате, издаваемый верблюдом
     */
    @Override
    public String response() {
        return super.response() + "хры";
    }

    /**
     * Краткое представление объекта верблюд
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Верблюд " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Camel
     * @return String c описание объекта типа Camel
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: верблюд, потребление воды: " + this.waterConsumption;
    }

}
