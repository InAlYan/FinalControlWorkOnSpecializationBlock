package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Dog extends Pet {

    /**
     * Агрессивность к незнакомцам (специфическое свойство собак)
     */
    protected int aggressiveness;

    /**
     * Возвращает агрессивность к незнакомцам (специфическое свойство собак) типа int
     * @return агрессивность к незнакомцам (специфическое свойство собак) типа int
     */
    public int getAggressiveness() {
        return aggressiveness;
    }

    /**
     * Устанавливает агрессивность к незнакомцам (специфическое свойство собак) типа int
     * @param aggressiveness агрессивность к незнакомцам (специфическое свойство собак) типа int
     */
    public void setAggressiveness(int aggressiveness) {
        this.aggressiveness = aggressiveness;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param friendliness - дружелюбность типа int
     * @param aggressiveness - агрессивность к незнакомцам типа int
     */
    private Dog(int animalId, String nickname, Date birthday, int friendliness, int aggressiveness) {
        super(animalId, nickname, birthday, friendliness);
        this.aggressiveness = aggressiveness;
    }

    /**
     * Фабричный метод создания экземпляра класса Dog
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка собаки
     * @param birthday - String день рождения собаки
     * @param friendliness - int дружелюбность собаки в процентах (0-100%)
     * @param aggressiveness - int агрессивность собаки к чужакам в процентах (0-100%)
     * @return экземпляр класса Dog
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Dog create(int animalId, String nickname, String birthday, int friendliness, int aggressiveness)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Dog.create(animalId, nickname, birthdayDate, friendliness, aggressiveness);
    }

    /**
     * Фабричный метод создания экземпляра класса Dog
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка собаки
     * @param birthday - Date день рождения собаки
     * @param friendliness - int дружелюбность собаки в процентах (0-100%)
     * @param aggressiveness - int агрессивность собаки к чужакам в процентах (0-100%)
     * @return экземпляр класса Dog
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Dog create(int animalId, String nickname, Date birthday, int friendliness, int aggressiveness)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException  {
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
            throw new AnimalBirthdayException("Собака еще не родилась: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Собаке больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что дружелюбность число от 0 до 100
        if (friendliness < 0 || friendliness > 100)
            throw new AnimalSpecificTypeException("Дружелюбность должна быть от 0 до 100 %: ", friendliness);

        if (aggressiveness < 0 || aggressiveness > 100)
            throw new AnimalSpecificTypeException("Агрессивность к чужим должна быть от 0 до 100 %: ", aggressiveness);

        return new Dog(animalId, nickname, birthday, friendliness, aggressiveness);
    }

    /**
     * Так звучит собака
     * @return звук в текстовом формате, издаваемый собакой
     */
    @Override
    public String response() {
        return super.response() + "гаф";
    }

    /**
     * Краткое представление объекта собака
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Собака " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Dog
     * @return String c описание объекта типа Dog
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: собака, агрессивность к незнакомцам: " + this.aggressiveness;
    }

}