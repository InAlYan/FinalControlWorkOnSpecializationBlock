package Model.Domain;

import Model.Exceptions.AnimalBirthdayException;
import Model.Exceptions.AnimalNickNameException;
import Model.Exceptions.AnimalSimpleDateFormatException;
import Model.Exceptions.AnimalSpecificTypeException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Hamster extends Pet {

    /**
     * Активность
     */
    protected int activeness;

    /**
     * Возвращает активность (специфическое свойство хомяков) типа int
     * @return активность (специфическое свойство хомяков) типа int
     */
    public int getActiveness() {
        return activeness;
    }

    /**
     * Устанавливает активность (специфическое свойство хомяков) типа int
     * @param activeness активность (специфическое свойство хомяков) типа int
     */
    public void setActiveness(int activeness) {
        this.activeness = activeness;
    }

    /**
     * Конструктор
     * @param animalId - идентификатор типа int
     * @param nickname - кличка типа String
     * @param birthday - день рождения типа Date
     * @param friendliness - дружелюбность типа int
     * @param activeness - активность типа int
     */
    private Hamster(int animalId, String nickname, Date birthday, int friendliness, int activeness) {
        super(animalId, nickname, birthday, friendliness);
        this.activeness = activeness;
    }

    /**
     * Фабричный метод создания экземпляра класса Hamster
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка хомяка
     * @param birthday - String день рождения хомяка
     * @param friendliness - int дружелюбность хомяка в процентах (0-100%)
     * @param activeness - int активность в процентах (0-100%)
     * @return экземпляр класса Hamster
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Hamster create(int animalId, String nickname, String birthday, int friendliness, int activeness)
            throws AnimalNickNameException, AnimalBirthdayException, AnimalSpecificTypeException, AnimalSimpleDateFormatException {

        if (dateFormat == null)
            throw new AnimalSimpleDateFormatException("Не задан формат даты! Задайте его с помощью метода Animal.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd\")) например...");

        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            throw new AnimalBirthdayException("Не могу распознать в текущем формате " + dateFormat.toPattern() + " такую дату: " , birthday);
        }

        return Hamster.create(animalId, nickname, birthdayDate, friendliness, activeness);
    }

    /**
     * Фабричный метод создания экземпляра класса Hamster
     * @param animalId - идентификатор типа int
     * @param nickname - String кличка хомяка
     * @param birthday - Date день рождения хомяка
     * @param friendliness - int дружелюбность хомяка в процентах (0-100%)
     * @param activeness - int активность хомяка в процентах (0-100%)
     * @return экземпляр класса Hamster
     * @throws AnimalNickNameException (некорректная кличка не англ/рус буквы или пробелы, длина меньше 2 или больше 50)
     * @throws AnimalBirthdayException (некорректная дата или дата из будущего или из прошлого, удаленного более чем на 100 лет)
     * @throws AnimalSpecificTypeException (некорректная специфичные свойства менее 0 или более 100 процентов)
     * @throws AnimalSimpleDateFormatException (некорректный формат данных представления даты типа SimpleDateFormat)
     */
    public static Hamster create(int animalId, String nickname, Date birthday, int friendliness, int activeness)
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
            throw new AnimalBirthdayException("Хомяк еще не родился: ", dateFormat.format(birthday));
        //Более ста лет
        if (birthday.before(calendar.getTime()))
            throw new AnimalBirthdayException("Хомяку больше ста лет: ", dateFormat.format(birthday));

        // Проверяем, что дружелюбность число от 0 до 100
        if (friendliness < 0 || friendliness > 100)
            throw new AnimalSpecificTypeException("Дружелюбность должна быть от 0 до 100 %: ", friendliness);

        if (activeness < 0 || activeness > 100)
            throw new AnimalSpecificTypeException("Активность должна быть от 0 до 100 %: ", activeness);

        return new Hamster(animalId, nickname, birthday, friendliness, activeness);
    }

    /**
     * Так звучит хомяк
     * @return звук в текстовом формате, издаваемый хомяком
     */
    @Override
    public String response() {
        return super.response() + "пи";
    }

    /**
     * Краткое представление объекта хомяк
     * @return String c кратким описанием (вид животного + идентификатор + кличка + дата рождения) объекта типа Animal
     */
    @Override
    public String shortDescription() {
        return "Хомяк " + super.shortDescription();
    }

    /**
     * Переопределенное представление объекта Hamster
     * @return String c описание объекта типа Hamster
     */
    @Override
    public String toString() {
        return super.toString() + "\nвид: хомяк, активность: " + this.activeness;
    }

}
