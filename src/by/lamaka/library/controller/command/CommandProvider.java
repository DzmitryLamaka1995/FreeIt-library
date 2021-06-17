package by.lamaka.library.controller.command;

import by.lamaka.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<Integer, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(1, new AddBookCommand());
        commands.put(2, new EditBookCommand());
        commands.put(3, new PrintAllBooksCommand());
        commands.put(4, new PrintAllAuthorsCommand());
        commands.put(5, new PrintBooksByAuthorCommand());
        commands.put(6, new RemoveBookCommand());
    }

    public Command getCommand(int number) {
        return commands.get(number);
    }
}
