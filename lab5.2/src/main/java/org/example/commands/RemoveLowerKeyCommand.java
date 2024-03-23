package org.example.commands;

import org.example.creating.LabWorkCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

import java.util.Map;
import java.util.TreeMap;

public class RemoveLowerKeyCommand extends Command{
    private ConsoleManager console;
    private CollectionManager collectionManager;
    public RemoveLowerKeyCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("remove_lower_key", " удалить из коллекции все элементы, " +
                "ключ которых меньше, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument){

        if (argument.isEmpty()) {
            console.printError("Введите значение ключа");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("Ключ должен быть числом");
            return false;
        }
        Integer key = Integer.parseInt(argument);
        TreeMap<Integer, LabWork> newCollection = new TreeMap<>();
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            if (entry.getKey() >= key) {
                newCollection.put(entry.getKey(), entry.getValue());
            }
        }
        collectionManager.updateCollection(newCollection);
        return true;
    }
}
