package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;
import utility.ExecutionResponse;

/**проверка
 * Вывести любой объект из коллекции, значение поля coordinates которого является минимальным
 */
public class MinByCoordinates extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public MinByCoordinates(Console console, CollectionManager collectionManager) {
        super("min_by_coordinates", "Вывести любой объект из коллекции, значение поля coordinates которого является минимальным");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        LabWork min = null;
        for (var e : collectionManager.getCollection()) {
            if (min==null || min.getCoordinates().getX() > e.getCoordinates().getX()) min = e;
        }
        if (min == null) {return new ExecutionResponse(false, "LabWork не обнаружено.");}
        else {return new ExecutionResponse(min.toString());}
    }
}