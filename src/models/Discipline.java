package models;

import utility.Element;
import utility.Validatable;

public class Discipline implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long practiceHours; //Поле не может быть null

    public Discipline (String name, Long practiceHours) {
        this.name = name;
        this.practiceHours = practiceHours;
    }

    @Override
    public boolean validate() {
        if (name == null || name.isEmpty()) return false;
        return practiceHours != null;
    }
}
