package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Donkey extends PackAnimal {

    /**
     * Упрямство
     */
    protected int stubbornness;

    /**
     * Возвращает упрямство (специфическое свойство ослов) типа int
     * @return упрямство (специфическое свойство ослов) типа int
     */
    public int getStubbornness() {
        return stubbornness;
    }

    /**
     * Устанавливает упрямство (специфическое свойство ослов) типа int
     * @param stubbornness упрямство (специфическое свойство ослов) типа int
     */
    public void setStubbornness(int stubbornness) {
        this.stubbornness = stubbornness;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param loadCapacity - грузоподъемность типа int
     * @param stubbornness - упрямство типа int
     */
    private Donkey(int animalId, String nickname, Date birthday, int loadCapacity, int stubbornness) {
        super(animalId, nickname, birthday, loadCapacity);
        this.stubbornness = stubbornness;
    }

    /**
     * Фабричный метод создания экземпляра класса Donkey
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка осла
     * @param birthday - String день рождения осла
     * @param loadCapacity - int грузоподъемность осла в кг (0-1000 кг)
     * @param stubbornness - int упрямство осла (0-100 %)
     * @return экземпляр класса Donkey
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000 кг или 100 %)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Donkey create(int animalId, String nickname, String birthday, int loadCapacity, int stubbornness)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Donkey.create(animalId, nickname, birthdayDate, loadCapacity, stubbornness);
    }

    /**
     * Фабричный метод создания экземпляра класса Donkey
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка осла
     * @param birthday - Date день рождения осла
     * @param loadCapacity - int грузоподъемность осла в кг (0-1000 кг)
     * @param stubbornness - int упрямство осла (0-100 %)
     * @return экземпляр класса Donkey
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 1000)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Donkey create(int animalId, String nickname, Date birthday, int loadCapacity, int stubbornness)
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
            throw new AnimalBirthdayException("Осел еще не родился: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Ослу больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что дружелюбность число от 0 до 100
        if (loadCapacity < 0 || loadCapacity > 1000)
            throw new AnimalSpecificTypeException("Грузоподъемность должна быть от 0 до 1000 кг : ", loadCapacity);

        if (stubbornness < 0 || stubbornness > 100)
            throw new AnimalSpecificTypeException("Упрямство должно быть от 0 до 100 %: ", stubbornness);

        return new Donkey(animalId, nickname, birthday, loadCapacity, stubbornness);
    }

    /**
     * Так звучит осел
     * @return звук в текстовом формате, издаваемый ослом
     */
    @Override
    public String response() {
        return super.response() + "хии-хоо";
    }

    /**
     * Краткое представление объекта осёл
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Осёл " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Donkey
     * @return String c описание объекта типа Donkey
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: осел, упрямство: " + this.stubbornness;
    }
    
}