package commands;
////////////////////////???????????ПРОВЕРКА
import models.LabWork;
import managers.CollectionManager;
import utility.Console;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.Vector;

/**
 * Вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintAscending(Console console, CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**???????????????????
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        Vector<LabWork> CollectionCopy = collectionManager.getCollection();
        Comparator<LabWork> comparator = new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
//                 Здесь нужно определить логику сравнения объектов LabWork
//                 Например, если есть поле id:
                return Integer.compare(o1.getId(), o2.getId());
//                 Или если нужно сравнивать по нескольким полям:
//                 int nameComparison = o1.getName().compareTo(o2.getName());
//                 if (nameComparison != 0) return nameComparison;
//                 return Integer.compare(o1.getId(), o2.getId());
            }
        };
        Collections.sort(CollectionCopy, comparator);
//        Collections.sort(CollectionCopy);
        for (LabWork labWork : CollectionCopy) {
            console.println(labWork.toString());
        }
        return true;
    }
}