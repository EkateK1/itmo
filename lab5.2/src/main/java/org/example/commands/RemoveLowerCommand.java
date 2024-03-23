package org.example.commands;

import org.example.creating.LabWorkCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

public class RemoveLowerCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public RemoveLowerCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("remove_lower", " удалить из коллекции все элементы, меньшие, чем заданный");
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
        collectionManager.getCollection().entrySet().removeIf(entry -> entry.getValue().compareTo(labWork) < 0);
        return true;
    }
}
