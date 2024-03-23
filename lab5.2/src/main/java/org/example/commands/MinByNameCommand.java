package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

import java.util.Map;

public class MinByNameCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public MinByNameCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("min_by_name", "вывести любой объект из коллекции, " +
                "значение поля name которого является минимальным");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов");
        }
        LabWork labWork = collectionManager.getFirstElement();
        if (labWork == null) {
            console.printError("Коллекция пуста");
            return false;
        }
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            if (entry.getValue().getName().compareTo(labWork.getName()) < 0) {
                labWork = entry.getValue();
            }
        }
        console.println(labWork);
        return true;
    }
}
