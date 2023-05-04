package ru.sayron.server.commands;

import ru.sayron.client.client.OrganizationAsker;
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
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
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

            if (organizationAsker.askQuestion("Хотите изменить название организации?")) name = organizationAsker.askName();
            if (organizationAsker.askQuestion("Хотите изменить координаты организации?")) coordinates = organizationAsker.askCoordinates();
            if (organizationAsker.askQuestion("Хотите изменить годовой оборот организации?")) turnover = organizationAsker.askTurnover();
            if (organizationAsker.askQuestion("Хотите изменить полное название организации?")) fullName = organizationAsker.askFullName();
            if (organizationAsker.askQuestion("Хотите изменить количество работников организации?")) employeesCount = organizationAsker.askEmployeesCount();
            if (organizationAsker.askQuestion("Хотите изменить тип организации?")) type = organizationAsker.askType();
            if (organizationAsker.askQuestion("Хотите изменить адрес организации?")) officialAddress = organizationAsker.askAddress();

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
            Outputer.println("Организация успешно изменена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Outputer.printerror("ID должен быть представлен числом!");
        } catch (OrganizationNotFoundException exception) {
            Outputer.printerror("Солдата с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}