package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.exceptions.IllegalKeyException;
import org.example.exceptions.NoElementWithThatIdException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class UpdateCommand extends Command {
    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }
    @Override
    public Response execute(Request request){
        try {if (request.getArgument().isEmpty()) {
            return new Response(Status.ASK_ID, "Введите id для обновления элемента");
        }
        if (!request.getArgument().matches("\\d+")) throw new IllegalKeyException();

        Integer id = Integer.parseInt(request.getArgument());

        if (collectionManager.getCollectionById(id) == null ) throw new NoElementWithThatIdException();

        if (request.getLabWork() == null){
            return new Response(Status.ASK_LABWORK, "Обновите данные лабораторной работы");
        }

        collectionManager.removeById(id);
        if (collectionManager.addById(id, request.getLabWork())){
            return new Response(Status.OK, "Элемент успешно обновлен");
        }
        return new Response(Status.ERROR, "Произошла ошибка при обновлении элемента");
        } catch (IllegalKeyException e){
            return new Response(Status.WRONG_ARGUMENT, e.getMessage());
        } catch (NoElementWithThatIdException e){
                return new Response(Status.ERROR, e.getMessage());
        }
    }
}
