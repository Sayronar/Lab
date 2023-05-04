package ru.sayron.server.commands;


import ru.sayron.common.exceptions.WrongAmountOfElementsException;
import ru.sayron.common.utility.Outputer;

public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Outputer.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
