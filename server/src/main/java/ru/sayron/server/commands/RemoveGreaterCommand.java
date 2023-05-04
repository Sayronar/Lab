package ru.sayron.server.commands;

import ru.sayron.common.data.Organization;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;
import ru.sayron.client.client.OrganizationAsker;

import java.time.LocalDateTime;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private OrganizationAsker organizationAsker;

    public RemoveGreaterCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.organizationAsker = organizationAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Organization organizationToFind = new Organization(
                    collectionManager.generateNextId(),
                    organizationAsker.askName(),
                    organizationAsker.askCoordinates(),
                    LocalDateTime.now(),
                    organizationAsker.askTurnover(),
                    organizationAsker.askFullName(),
                    organizationAsker.askEmployeesCount(),
                    organizationAsker.askType(),
                    organizationAsker.askAddress()
            );
            Organization organizationFromCollection = collectionManager.getByValue(organizationToFind);
            if (organizationFromCollection == null) throw new OrganizationNotFoundException();
            collectionManager.removeGreater(organizationFromCollection);
            Outputer.println("Организации успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("Коллекция пуста!");
        } catch (OrganizationNotFoundException exception) {
            Outputer.printerror("Организации с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
