package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

import java.util.Map;

public class ShowCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public ShowCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов, так как она выводит коллекцию");
        }
        collectionManager.printCollection(console);
        return true;
    }
}
