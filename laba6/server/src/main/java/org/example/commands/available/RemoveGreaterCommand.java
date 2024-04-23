package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class RemoveGreaterCommand extends Command {

    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        Integer id;
        if (!request.getArgument().isEmpty()){
            id = Integer.parseInt(request.getArgument());
        } else {
            return new Response(Status.ASK_ID, "Введите id элемента");
        }
        if (request.getLabWork() == null){
            return new Response(Status.ASK_LABWORK, "");
        }
        LabWork labWork = request.getLabWork();
        labWork.setId(id);
        collectionManager.getCollection().entrySet().removeIf(entry -> entry.getValue().compareTo(labWork) > 0);
        return new Response(Status.OK, "Элементы успешно удалены");
    }
}
