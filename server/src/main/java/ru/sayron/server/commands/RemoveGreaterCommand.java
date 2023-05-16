package ru.sayron.server.commands;

import ru.sayron.common.data.Organization;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.interaction.OrganizationRaw;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;
import ru.sayron.server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater","{element}", "remove from the collection all elements greater than the given");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            OrganizationRaw organizationRaw = (OrganizationRaw) objectArgument;
            Organization organizationToFind = new Organization(
                    collectionManager.generateNextId(),
                    organizationRaw.getName(),
                    organizationRaw.getCoordinates(),
                    LocalDateTime.now(),
                    organizationRaw.getAnnualTurnover(),
                    organizationRaw.getFullName(),
                    organizationRaw.getEmployeesCount(),
                    organizationRaw.getType(),
                    organizationRaw.getOfficialAddress()
            );
            Organization organizationFromCollection = collectionManager.getByValue(organizationToFind);
            if (organizationFromCollection == null) throw new OrganizationNotFoundException();
            collectionManager.removeGreater(organizationFromCollection);
            Outputer.println("Organizations deleted successfully!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("The collection is empty!");
        } catch (OrganizationNotFoundException exception) {
            Outputer.printerror("There are no organizations with such characteristics in the collection!");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("The object passed by the client is invalid!");
        }
        return false;
    }
}
