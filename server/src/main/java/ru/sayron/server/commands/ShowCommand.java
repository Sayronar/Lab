package ru.sayron.server.commands;

import ru.sayron.server.utility.CollectionManager;
import ru.sayron.common.exceptions.*;
import ru.sayron.common.utility.Outputer;
/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Outputer.println(collectionManager);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}