package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

import java.util.Map;

public class AverageOfMaximumPointCommand extends Command {

    private CollectionManager collectionManager;

    public AverageOfMaximumPointCommand(CollectionManager collectionManager) {
        super("average_of_maximum_point", "вывести среднее значение поля maximumPoint " +
                "для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        double sum = 0;
        double amount = 0;
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            sum += entry.getValue().getMaximumPoint();
            amount += 1;
        }
        return new Response(Status.OK, "Среднее значение поля maximumPoint для всех элементов коллекции " +
                (amount != 0 ? (sum / amount) : "0"));
    }
}
