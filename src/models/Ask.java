package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import managers.CollectionManager;
import models.*;
import utility.Console;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

/**
 * Класс чтения объекта
 */
public class Ask {
    public static class AskBreak extends Exception {}

    public static LabWork askLabWork(Console console, CollectionManager collectionManager) throws AskBreak {
        /**
         *     private String name; //Поле не может быть null, Строка не может быть пустой+
         *     private Coordinates coordinates; //Поле не может быть null+
         *     java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
         *     private Double minimalPoint; //Поле может быть null, Значение поля должно быть больше 0+
         *     private String description; //Длина строки не должна быть больше 5287, Поле не может быть null
         *     private int tunedInWorks;
         *     private Difficulty difficulty; //Поле не может быть null
         *     private Discipline discipline; //Поле не может быть null
         */
        try {
            console.print("name: ");
            String name;
            while (true) {
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.equals("")) break;
                console.print("name: ");
            }
            var coordinates = askCoordinates(console);
            LocalDate creationDate;
            while (true) {
                console.print("creationDate: " +
                        LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME) + " or 2023-03-11): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.equals("")) {
                    creationDate = null;
                    break;
                }
                try {
                    creationDate = LocalDate.parse(line, DateTimeFormatter.ISO_DATE_TIME);
                    break;
                } catch (DateTimeParseException e) {}
                try {
                    creationDate = LocalDate.parse(line + "T00:00:00.0000", DateTimeFormatter.ISO_DATE_TIME);
                    break;
                } catch (DateTimeParseException e) {}
            }
            console.print("minimalPoint: ");
            Double minimalPoint;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (line.isEmpty()) {
                    minimalPoint = null;
                    break;
                }
                try {
                    minimalPoint = Double.parseDouble(line);
                    if (minimalPoint > 0) break;
                } catch (NumberFormatException e) {}
                console.print("minimalPoint: ");
            }
            console.print("description: ");
            String description;
            while (true) {
                description = console.readln().trim();
                if (description.equals("exit")) throw new AskBreak();
                if (!description.isEmpty()) break;
                if (description.length() <= 5287) break;
                console.print("description: ");
            }
            console.print("tunedInWorks: ");
            int tunedInWorks;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                try {
                    tunedInWorks = Integer.parseInt(line);
                    break;
                } catch (NumberFormatException e) {}
                console.print("tunedInWorks: ");
            }
            var difficulty = askDifficulty(console);
            var discipline = askDiscipline(console);
            return new LabWork(collectionManager.getFreeId(), name, coordinates, creationDate, minimalPoint, description, tunedInWorks, difficulty, discipline);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
//            private Integer x; //Поле не может быть null
//            private float y; //Максимальное значение поля: 654
            console.print("coordinates.x: ");
            Integer x;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { x = Integer.parseInt(line); break; } catch (NumberFormatException e) {}
                }
                console.print("coordinates.x: ");
            }
            console.print("coordinates.y: ");
            float y;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try { y = Float.parseFloat(line); if (y<=654) break; } catch (NumberFormatException e) { }
                }
                console.print("coordinates.y: ");
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Difficulty askDifficulty(Console console) throws AskBreak {
        try {
            console.print("Difficulty ("+Difficulty.names()+"): ");
            Difficulty r;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try { r = Difficulty.valueOf(line); break; } catch (NullPointerException | IllegalArgumentException  e) {}
                }
                console.print("Difficulty: ");
            }
            return r;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Discipline askDiscipline(Console console) throws AskBreak {
        try {
//            private String name; //Поле не может быть null, Строка не может быть пустой
//            private Long practiceHours; //Поле не может быть null
            console.print("name: ");
            String name;
            while (true) {
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.isEmpty()) break;
            }
            console.print("practiceHours: ");
            Long practiceHours;
            while (true) {
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.isEmpty()) {
                    try { practiceHours = Long.parseLong(line); break; } catch (NumberFormatException e) {}
                }
                console.print("practiceHours: ");
            }
            return new Discipline(name, practiceHours);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}