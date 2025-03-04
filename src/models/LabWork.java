package models;

import managers.CollectionManager;
import utility.Element;

import java.time.LocalDate;
import java.util.Objects;


public class LabWork extends Element {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле может быть null, Значение поля должно быть больше 0
    private String description; //Длина строки не должна быть больше 5287, Поле не может быть null
    private int tunedInWorks;
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле не может быть null

    private static int nextId = 1;

    public LabWork(int id, String name, Coordinates coordinates, LocalDate creationDate,
                   Double minimalPoint, String description, int tunedInWorks, Difficulty difficulty, Discipline discipline){

//    public LabWork(int id, String name, Coordinates coordinates, Double minimalPoint, String description, int tunedInWorks, Difficulty difficulty, Discipline discipline) {
        this.id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.description = description;
        this.tunedInWorks = tunedInWorks;
        this.difficulty = difficulty;
        this.discipline = discipline;
    }

    /**
     * Обновляет указатель следующего ID
     */
    public static void updateNextId(CollectionManager collectionManager) {
        var maxId = collectionManager
                .getCollection()
                .stream().filter(Objects::nonNull)
                .map(LabWork::getId)
                .mapToInt(Integer::intValue).max().orElse(0);
        nextId = maxId + 1;
    }

    public static String toArray(LabWork e) {
        return null;
    }

    public static LabWork fromArray() {
        return null;
    }

    /**
     * Валидирует правильность полей.
     */
    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (minimalPoint == null || minimalPoint <= 0) return false;
        if (description == null || description.length()<=5287) return false;
        if (difficulty == null) return false;
        if (discipline == null) return false;
        return true;
    }

    public void update(LabWork labWork) {
        this.name = labWork.name;
        this.coordinates = labWork.coordinates;
        this.creationDate = labWork.creationDate;
        this.minimalPoint = labWork.minimalPoint;
        this.description = labWork.description;
        this.tunedInWorks = labWork.tunedInWorks;
        this.difficulty = labWork.difficulty;
        this.discipline = labWork.discipline;
    }

    public static void touchNextId() {
        nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public String getDescription() {
        return description;
    }

    public int getTunedInWorks() {
        return tunedInWorks;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public int compareTo(Element element) {
        return (this.id - element.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return id == labWork.id && Objects.equals(name, labWork.name) && Objects.equals(coordinates, labWork.coordinates)
                && Objects.equals(creationDate, labWork.creationDate) && Objects.equals(minimalPoint, labWork.minimalPoint)
                && Objects.equals(description, labWork.description) && Objects.equals(tunedInWorks, labWork.tunedInWorks)
                && Objects.equals(difficulty, labWork.difficulty) && Objects.equals(discipline, labWork.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, minimalPoint, description, tunedInWorks, difficulty, discipline);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Продукт №" + id;
        info += "добавлен " + creationDate.toString();
        info += "\n Название: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Минимальная точка: " + minimalPoint;
        info += "\n Описание: " + description;
        info += "\n Настроенный в работах: " + tunedInWorks;
        info += "\n Сложность: " + difficulty;
        info += "\n Дисциплина: " + discipline;
        return info;
    }
}