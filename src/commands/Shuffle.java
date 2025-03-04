package commands;

/**требует проверки
 * Перемешать элементы коллекции в случайном порядке
 */
import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда shuffle - перемешать элементы коллекции в случайном порядке
 *
 * @author petrovviacheslav
 */
public class Shuffle extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Shuffle
     *
     * @param collectionManager менеджер коллекции
     * @param console           консоль
     */
    public Shuffle(Console console, CollectionManager collectionManager) {
        super("shuffle", "перемешать элементы коллекции в случайном порядке");
        this.collectionManager = collectionManager;
    }

    /**
     * Перемешивает коллекцию
     *
     * @param arguments аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(collectionManager.shuffle());
    }
}