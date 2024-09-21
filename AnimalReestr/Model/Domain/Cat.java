package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Cat extends Pet {

    /**
     * Сонливость
     */
    protected int sleepiness;

    /**
     * Возвращает сонливость (специфическое свойство кошек) типа int
     * @return Возвращает сонливость (специфическое свойство кошек) типа int
     */
    public int getSleepiness() {
        return sleepiness;
    }

    /**
     * Устанавливает сонливость (специфическое свойство кошек) типа int
     * @param sleepiness сонливость (специфическое свойство кошек) типа int
     */
    public void setSleepiness(int sleepiness) {
        this.sleepiness = sleepiness;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param friendliness - дружелюбность типа int
     * @param sleepiness - сонливость типа int
     */
    private Cat(int animalId, String nickname, Date birthday, int friendliness, int sleepiness) {
        super(animalId, nickname, birthday, friendliness);
        this.sleepiness = sleepiness;
    }

    /**
     * Фабричный метод создания экземпляра класса Cat
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка кошки
     * @param birthday - String день рождения кошки
     * @param friendliness - int дружелюбность кошки в процентах (0-100%)
     * @param sleepiness - int сонливость в процентах (0-100%)
     * @return экземпляр класса Cat
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Cat create(int animalId, String nickname, String birthday, int friendliness, int sleepiness)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Cat.create(animalId, nickname, birthdayDate, friendliness, sleepiness);
    }

    /**
     * Фабричный метод создания экземпляра класса Cat
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка кошки
     * @param birthday - Date день рождения кошки
     * @param friendliness - int дружелюбность кошки в процентах (0-100%)
     * @param sleepiness - int сонливость кошки в процентах (0-100%)
     * @return экземпляр класса Cat
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Cat create(int animalId, String nickname, Date birthday, int friendliness, int sleepiness)
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
            throw new AnimalBirthdayException("Кошка еще не родилась: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Кошке больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что дружелюбность число от 0 до 100
        if (friendliness < 0 || friendliness > 100)
            throw new AnimalSpecificTypeException("Дружелюбность должна быть от 0 до 100 %: ", friendliness);

        if (sleepiness < 0 || sleepiness > 100)
            throw new AnimalSpecificTypeException("Сонливость должна быть от 0 до 100 %: ", sleepiness);

        return new Cat(animalId, nickname, birthday, friendliness, sleepiness);
    }

    /**
     * Так звучит кошка
     * @return звук в текстовом формате, издаваемый кошкой
     */
    @Override
    public String response() {
        return super.response() + "мяу";
    }

    /**
     * Краткое представление объекта кошка
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Кошка " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Cat
     * @return String c описание объекта типа Cat
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: кошка, сонливость: " + this.sleepiness;
    }
}