package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

import java.util.Map;

public class MinByNameCommand extends Command {
    private CollectionManager collectionManager;

    public MinByNameCommand(CollectionManager collectionManager) {
        super("min_by_name", "вывести любой объект из коллекции, " +
                "значение поля name которого является минимальным");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        LabWork labWork = collectionManager.getFirstElement();
        if (labWork == null) {
            return new Response(Status.ERROR, "Коллекция пуста");
        }
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            if (entry.getValue().getName().compareTo(labWork.getName()) < 0) {
                labWork = entry.getValue();
            }
        }
        return new Response(Status.OK, labWork.toString());
    }
}
