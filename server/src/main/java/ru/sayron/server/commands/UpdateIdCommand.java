package ru.sayron.server.commands;

import ru.sayron.client.utility.OrganizationAsker;
import ru.sayron.common.data.*;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

import java.time.LocalDateTime;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private OrganizationAsker organizationAsker;

    public UpdateIdCommand(CollectionManager collectionManager, OrganizationAsker organizationAsker) {
        super("update","<ID> {element}", "update collection element value by ID");
        this.collectionManager = collectionManager;
        this.organizationAsker = organizationAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument) {
        try {
            if (argument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            Organization organization = collectionManager.getById(id);
            if (organization == null) throw new OrganizationNotFoundException();

            String name = organization.getName();
            Coordinates coordinates = organization.getCoordinates();
            LocalDateTime creationDate = organization.getCreationDate();
            int turnover = organization.getAnnualTurnover();
            String fullName = organization.getFullName();
            Long employeesCount = organization.getEmployeesCount();
            OrganizationType type = organization.getType();
            Address officialAddress = organization.getOfficialAddress();

            collectionManager.removeFromCollection(organization);

            if (organizationAsker.askQuestion("Do you want to change the name of the organization?")) name = organizationAsker.askName();
            if (organizationAsker.askQuestion("Do you want to change the coordinates of the organization?")) coordinates = organizationAsker.askCoordinates();
            if (organizationAsker.askQuestion("Do you want to change the annual turnover of the organization?")) turnover = organizationAsker.askTurnover();
            if (organizationAsker.askQuestion("Do you want to change the full name of the organization?")) fullName = organizationAsker.askFullName();
            if (organizationAsker.askQuestion("Do you want to change the number of employees in your organization?")) employeesCount = organizationAsker.askEmployeesCount();
            if (organizationAsker.askQuestion("Do you want to change the organization type?")) type = organizationAsker.askType();
            if (organizationAsker.askQuestion("Do you want to change the address of the organization?")) officialAddress = organizationAsker.askAddress();

            collectionManager.addToCollection(new Organization(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    turnover,
                    fullName,
                    employeesCount,
                    type,
                    officialAddress
            ));
            Outputer.println("The organization has been successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("The collection is empty!");
        } catch (NumberFormatException exception) {
            Outputer.printerror("ID must be represented by a number!");
        } catch (OrganizationNotFoundException exception) {
            Outputer.printerror("There is no soldier with this ID in the collection!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}