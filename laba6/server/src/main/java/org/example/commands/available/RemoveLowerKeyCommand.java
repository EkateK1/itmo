package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.exceptions.IllegalKeyException;
import org.example.managers.CollectionManager;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

import java.util.Map;
import java.util.TreeMap;

public class RemoveLowerKeyCommand extends Command {
    private CollectionManager collectionManager;
    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        super("remove_lower_key", " удалить из коллекции все элементы, " +
                "ключ которых меньше, чем заданный");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){

        try{if (request.getArgument().isEmpty()) {
            return new Response(Status.ERROR, "Введите ключ для добавления элемента");
        }
        if (!request.getArgument().matches("\\d+")) throw new IllegalKeyException();

        Integer key = Integer.parseInt(request.getArgument());
        TreeMap<Integer, LabWork> newCollection = new TreeMap<>();
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            if (entry.getKey() >= key) {
                newCollection.put(entry.getKey(), entry.getValue());
            }
        }
        collectionManager.updateCollection(newCollection);
        return new Response(Status.OK, "Элементы успешно удалены");
        } catch (IllegalKeyException e){
            return new Response(Status.WRONG_ARGUMENT, e.getMessage());
        }
    }
}
