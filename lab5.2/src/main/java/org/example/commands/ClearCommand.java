package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class ClearCommand extends Command{
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument){
        collectionManager.clearCollection();
        return true;
    }
}
