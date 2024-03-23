package org.example.commands;

import org.example.creating.LabWorkCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

public class RemoveGreaterCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String agrument){
        Integer id;
        if (!agrument.isEmpty()){
            id = Integer.parseInt(agrument);
        } else {
            id = LabWorkCreating.labWorkSetId(console);
        }
        LabWork labWork = LabWorkCreating.labWorkCreating(console);
        labWork.setId(id);
        collectionManager.getCollection().entrySet().removeIf(entry -> entry.getValue().compareTo(labWork) > 0);
        return true;
    }
}
