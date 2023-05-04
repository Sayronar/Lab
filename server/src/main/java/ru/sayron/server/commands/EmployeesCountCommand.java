package ru.sayron.server.commands;


import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

public class EmployeesCountCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public EmployeesCountCommand(CollectionManager collectionManager) {
        super("count_by_employees_count", "вывести количество элементов, значение поля employeesCount которых равно заданному");
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
            String filteredInfo = String.valueOf(collectionManager.countByEmployeesCount(employeesCount));
            if (!filteredInfo.isEmpty()) {
                Outputer.println(filteredInfo);
                return true;
            } else Outputer.println("В коллекции нет организаций с равным заданному количеству сотрудников!");
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