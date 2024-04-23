package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.exceptions.ElementAddingException;
import org.example.exceptions.IllegalKeyException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class InsertCommand extends Command {

    private CollectionManager collectionManager;

    public InsertCommand(CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        try {
            if (request.getArgument().isEmpty()) {
                return new Response(Status.ERROR, "Введите ключ для добавления элемента");
            }
            if (!request.getArgument().matches("\\d+")) throw new IllegalKeyException();

            Integer key = Integer.parseInt(request.getArgument());

            if (collectionManager.getCollectionById(key) != null) {
                return new Response(Status.WRONG_ARGUMENT, "Значение с таким ключом уже существует");
            }
            if (request.getLabWork() == null) {
                return new Response(Status.ASK_LABWORK,
                        "Создайте лабораторную работу, которую нужно добавить в коллекцию");
            } else {
                if (collectionManager.addById(key, request.getLabWork())) {
                    return new Response(Status.OK, "Элемент успешно добавлен");
                } else throw new ElementAddingException();
            }
        } catch (ElementAddingException e){
            return new Response(Status.ERROR, e.getMessage());
        } catch (IllegalKeyException e){
            return new Response(Status.WRONG_ARGUMENT, e.getMessage());
        }
    }
}

