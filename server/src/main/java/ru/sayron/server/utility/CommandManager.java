package ru.sayron.server.utility;

import ru.sayron.common.utility.Outputer;
import ru.sayron.server.commands.Command;
import ru.sayron.common.exceptions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Operates the commands.
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 15;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateIdCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command executeScriptCommand;
    private Command removeGreaterCommand;
    private Command removeLowerCommand;
    private Command historyCommand;
    private Command employeesCountCommand;
    private Command filterContainsNameCommand;
    private Command filterGreaterThanEmployeesCountCommand;

    private Command serverExitCommand;

    public CommandManager(Command helpCommand, Command infoCommand,
                          Command showCommand, Command addCommand, Command updateIdCommand, Command removeByIdCommand,
                          Command clearCommand, Command saveCommand, Command exitCommand, Command executeScriptCommand,
                          Command removeGreaterCommand, Command removeLowerCommand, Command historyCommand,
                          Command employeesCountCommand, Command filterContainsNameCommand,
                          Command filterGreaterThanEmployeesCountCommand, Command serverExitCommand) {
        this.commandHistory = commandHistory;
        this.commands = commands;
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateIdCommand = updateIdCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.exitCommand = exitCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.historyCommand = historyCommand;
        this.employeesCountCommand = employeesCountCommand;
        this.filterContainsNameCommand = filterContainsNameCommand;
        this.filterGreaterThanEmployeesCountCommand = filterGreaterThanEmployeesCountCommand;
        this.serverExitCommand = serverExitCommand;

        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateIdCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(exitCommand);
        commands.add(executeScriptCommand);
        commands.add(removeLowerCommand);
        commands.add(removeGreaterCommand);
        commands.add(historyCommand);
        commands.add(employeesCountCommand);
        commands.add(filterContainsNameCommand);
        commands.add(filterGreaterThanEmployeesCountCommand);
        commands.add(serverExitCommand);
    }

    /**
     * @return The command history.
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }

    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Adds command to command history.
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {
        for (Command command : commands) {
            if (command.getName() != null && command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    /**
     * Prints that command is not found.
     * @param command Comand, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Outputer.println("Command '" + command + "' not found. Type 'help' for help.");
        return false;
    }

    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Outputer.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return updateIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeLower(String argument) {
        return removeLowerCommand.execute(argument);
    }

    /**
     * Prints the history of used commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();

                Outputer.println("Last used commands:");
                for (int i=0; i<commandHistory.length; i++) {
                    if (commandHistory[i] != null) Outputer.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Outputer.println("No commands have been used yet!");
            }
        }
        return false;
    }

    /**
     * Executes needed command.
     // * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean countByEmployeesCount(String argument) {
        return employeesCountCommand.execute(argument);
    }

    public boolean filterContainsName(String argument) {
        return filterContainsNameCommand.execute(argument);
    }

    /**
     * Executes needed command.
     // * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterGreaterThanEmployeesCount(String argument) {
        return filterGreaterThanEmployeesCountCommand.execute(argument);
    }

    /**
     * Adds command to command history.
     *
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE - 1; i > 0; i--) {
                    commandHistory[i] = commandHistory[i - 1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }

    @Override
    public String toString() {
        return "CommandManager (helper class for working with commands)";
    }
}

