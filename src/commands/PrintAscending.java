//package commands;
//
///**petrovviacheslav????
// * Вывести элементы коллекции в порядке возрастания
// */
//
//public class PrintAscending {
//}

package commands;

import managers.CollectionManager;
import utility.Console;
import models.LabWork;
import utility.ExecutionResponse;
import java.util.Vector;
import java.util.Collections;

/**
 * Вывести элементы коллекции в порядке возрастания
 */
public class PrintAscending extends Command {
    private final CollectionManager collectionManager;

    public PrintAscending(Console console, CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    /**
     * Выводит элементы коллекции в порядке возрастания
     *
     * @param arguments аргумент команды
     * @return true - команда выполнена успешно, иначе false
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        Vector<LabWork> CollectionCopy = collectionManager.getCollection();
        Collections.sort(CollectionCopy);
        for (LabWork labWork : CollectionCopy) {
            return new ExecutionResponse(labWork.toString());
        }
        return null;
    }
}
//    @Override
//    public boolean execute(String[] args) {
//        try {
//            if (!args[1].isEmpty()) throw new WrongCommandArgsException();
//            Stack<StudyGroup> copyOfStackCollection = collectionManager.getStackCollection();
//            Collections.sort(copyOfStackCollection);
//            for (StudyGroup studyGroup : copyOfStackCollection) {
//                console.println(studyGroup.toString());
//            }
//            return true;
//        } catch (WrongCommandArgsException e) {
//            console.printerror(e.toString());
//            return false;
//        }
//    }

//    /**????из другого ну может помочь мне кажется
//     * Выполняет команду
//     * @return Успешность выполнения команды.
//     */
//    @Override
//    public boolean apply(String[] arguments) {
//        try {
//            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
//
//            var sumOfPrice = getSumOfPrice();
//            if (sumOfPrice == 0) throw new CollectionIsEmptyException();
//
//            console.println("Сумма цен всех продуктов: " + sumOfPrice);
//            return true;
//        } catch (WrongAmountOfElementsException exception) {
//            console.println("Использование: '" + getName() + "'");
//        } catch (CollectionIsEmptyException exception) {
//            console.println("Коллекция пуста!");
//        }
//        return false;
//    }


