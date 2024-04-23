package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.exceptions.ElementAddingException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class AddCommand extends Command {

    private CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить элемент");
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request){
        try {
        if (request.getLabWork() == null) return new
                Response(Status.ASK_LABWORK, "Введите данные элемента для добавления");
        if (collectionManager.add(request.getLabWork())) return new
                Response(Status.OK, "Элемент успешно добавлен");
        else throw new ElementAddingException();
        } catch (ElementAddingException e){
            return new Response(Status.ERROR, e.getMessage());
        }
    }
}
