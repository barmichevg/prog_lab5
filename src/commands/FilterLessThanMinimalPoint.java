package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.ExecutionResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * вывести элементы, значение поля minimalPoint которых меньше заданного
 */
public class FilterLessThanMinimalPoint extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterLessThanMinimalPoint(Console console, CollectionManager collectionManager) {
        super("filter_less_than_minimal_point <minimalPoint>", "вывести элементы, значение поля minimalPoint которых меньше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        var minimalPoint = Double.parseDouble(arguments[1]);
        var LabWork = filterLessThanMinimalPoint(minimalPoint);

        if (LabWork.isEmpty()) {console.println("Продуктов с ценой " + minimalPoint + " не обнаружено.");}
        else {
            console.println("Продуктов с ценой " + minimalPoint + ": " + LabWork.size() + " шт.\n");
            LabWork.forEach(console::println);
        }
        return null;
    }
//        return true;
//
//        catch (NumberFormatException exception) {
//            console.printError("Цена должна быть представлена числом!");
//        }
//        return false;


    private List<LabWork> filterLessThanMinimalPoint(Double minimalPoint) {
        return collectionManager.getCollection().stream()
                .filter(labWork -> (labWork.getMinimalPoint() < (minimalPoint)))
                .collect(Collectors.toList());
    }
}