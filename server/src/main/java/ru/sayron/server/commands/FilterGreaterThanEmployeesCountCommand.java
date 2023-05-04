package ru.sayron.server.commands;


import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

public class FilterGreaterThanEmployeesCountCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterGreaterThanEmployeesCountCommand(CollectionManager collectionManager) {
        super("filter_greater_than_employees_count", "вывести элементы, значение поля employeesCount которых больше заданного");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long employeesCount = Long.parseLong(argument);
            String filteredInfo = collectionManager.employeesCountFilteredInfo(employeesCount);
            if (!filteredInfo.isEmpty()) {
                Outputer.println(filteredInfo);
                return true;
            } else Outputer.println("В коллекции нет организаций с количеством сотрудников больше, чем заданное!");
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("Коллекция пуста!");
        } catch (IllegalArgumentException exception) {
            Outputer.printerror("Организации нет в списке!");
        }
        return false;
    }
}