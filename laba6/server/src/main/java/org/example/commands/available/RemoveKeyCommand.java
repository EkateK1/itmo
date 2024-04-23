package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.exceptions.IllegalKeyException;
import org.example.exceptions.NoElementWithThatIdException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

public class RemoveKeyCommand extends Command {

    private CollectionManager collectionManager;

    public RemoveKeyCommand(CollectionManager collectionManager) {
        super("remove_key", "удалить элемент из коллекции по его ключу");
        this.collectionManager = collectionManager;
    }
    @Override
    public Response execute(Request request){
       try{ if (request.getArgument().isEmpty()) {
            return new Response(Status.ERROR, "Введите ключ для добавления элемента");
        }
        if (!request.getArgument().matches("\\d+")) throw new IllegalKeyException();
        Integer key = Integer.parseInt(request.getArgument());
        if (collectionManager.getCollectionById(key) == null ) throw new NoElementWithThatIdException();
        collectionManager.removeById(key);
        return new Response(Status.OK, "Элемент успешно удален");
       } catch (IllegalKeyException e){
           return new Response(Status.WRONG_ARGUMENT, e.getMessage());
       } catch (NoElementWithThatIdException e){
           return new Response(Status.WRONG_ARGUMENT, e.getMessage());
       }
    }
}
