package org.example.commands;

import org.example.creating.LabWorkCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class AddCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public AddCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("add", "добавить элемент");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Поля элемента следует ввести позже");
        }
        if (collectionManager.add(LabWorkCreating.labWorkCreating(console))) return true;
        console.printError("Произошла ошибка при обновлении элемента");
        return false;
    }
}
