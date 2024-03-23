package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class RemoveKeyCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public RemoveKeyCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите ключ для удаления элемента");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("Ключ должен быть числом");
            return false;
        }
        Integer key = Integer.parseInt(argument);
        if (collectionManager.getCollectionById(key) == null ){
            console.printError("Значения с таким ключом не существует");
            return false;
        }
        collectionManager.removeById(key);
        return true;
    }
}
