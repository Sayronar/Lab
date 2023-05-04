package ru.sayron.server.commands;

import ru.sayron.client.client.OrganizationAsker;
import ru.sayron.common.data.Organization;
import ru.sayron.common.exceptions.IncorrectInputInScriptException;
import ru.sayron.common.exceptions.WrongAmountOfElementsException;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

import java.time.LocalDateTime;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private OrganizationAsker organizationAsker;

    public AddCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            collectionManager.addToCollection(new Organization(
                    collectionManager.generateNextId(),
                    organizationAsker.askName(),
                    organizationAsker.askCoordinates(),
                    LocalDateTime.now(),
                    organizationAsker.askTurnover(),
                    organizationAsker.askFullName(),
                    organizationAsker.askEmployeesCount(),
                    organizationAsker.askType(),
                    organizationAsker.askAddress()
            ));
            Outputer.println("Организация успешно добавлена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}