package ru.sayron.server.commands;


import ru.sayron.common.data.Organization;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
import ru.sayron.server.utility.CollectionManager;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
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
            Long id = Long.parseLong(argument);
            Organization organizationToRemove = collectionManager.getById(id);
            if (organizationToRemove == null) throw new OrganizationNotFoundException();
            collectionManager.removeFromCollection(organizationToRemove);
            Outputer.println("Организация успешно удалена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Outputer.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Outputer.printerror("ID должен быть представлен числом!");
        } catch (OrganizationNotFoundException exception) {
            Outputer.printerror("Организации с таким ID в коллекции нет!");
        }
        return false;
    }
}