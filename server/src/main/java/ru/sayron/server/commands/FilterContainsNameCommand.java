package ru.sayron.server.commands;


import ru.sayron.common.data.Organization;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

public class FilterContainsNameCommand extends AbstractCommand {
    private Organization organization;
    private CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super("filter_contains_name <name>",
                "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            String containsName = argument;
            String filteredInfo = collectionManager.containsNameFilteredInfo(containsName);
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