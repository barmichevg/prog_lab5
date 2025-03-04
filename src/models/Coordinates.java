package models;

import utility.Validatable;

import java.util.Objects;

public class Coordinates implements Validatable {
    private Integer x; //Поле не может быть null
    private float y; //Максимальное значение поля: 654

    public Coordinates (Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return возвращает x
     */
    public long getX() {
        return x;
    }

    /**
     * Валидирует правильность полей.
     */
    @Override
    public boolean validate() {
        if (x == null) return false;
        return y <= 654;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
