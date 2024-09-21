package Model.Interfaces;

public interface IServiceBackup
{
    /**
     * Сохраняет список животных
     * @param fileName наименование файла для сохранения
     */
    void saveAnimals(String fileName);

    /**
     * Загружает список животных из файла в список животных типа ArrayList<Animal>
     * @param fileName наименование файла для загрузки
     * @return maxId типа int максимальный идентификатор объекта типа Animal из списка загруженных
     */
    int loadAnimals(String fileName);

}
