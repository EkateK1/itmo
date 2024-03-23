package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

import java.util.Map;

public class AverageOfMaximumPointCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public AverageOfMaximumPointCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("average_of_maximum_point", "вывести среднее значение поля maximumPoint " +
                "для всех элементов коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (!argument.isEmpty()){
            console.printError("Данная команда не имеет аргументов, так как она выводит информацию о коллекции");
        }
        double sum = 0;
        double amount = 0;
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            sum += entry.getValue().getMaximumPoint();
            amount += 1;
        }
        console.println("Среднее значение поля maximumPoint для всех элементов коллекции " +
                (amount != 0 ? (sum / amount) : "0"));
        return true;
    }
}
