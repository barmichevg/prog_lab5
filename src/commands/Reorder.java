package commands;

import managers.CollectionManager;
import utility.Console;
import utility.ExecutionResponse;

/**требует проверки
 * Отсортировать коллекцию в порядке, обратном нынешнему
 */
public class Reorder extends Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса Reorder
     *
     * @param collectionManager менеджер коллекции
     * @param console           консоль
     */
    public Reorder(Console console, CollectionManager collectionManager) {
        super("reorder", "отсортировать коллекцию в порядке, обратном нынешнему");
        this.collectionManager = collectionManager;
    }

    /**
     * Сортирует коллекцию в обратном порядке
     *
     * @param arguments вводимая в консоль строка
     * @return true - команда выполнена успешно, иначе false
     */
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(collectionManager.reorder());
    }
}

//    @Override
//    public boolean execute(String[] args) {
//        try {
//            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
//            collectionManager.reorder();
//            console.println("Коллекция отсортирована в обратном порядке");
//            return true;
//
//        } catch (WrongCommandArgsException e) {
//            console.printerror(e.toString());
//            return false;
//        }
//    }
//}