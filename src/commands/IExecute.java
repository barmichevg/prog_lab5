package commands;

/**
 * Вывести справку по доступным командам
 */

public interface IExecute {
    boolean apply(String[] arguments);
}