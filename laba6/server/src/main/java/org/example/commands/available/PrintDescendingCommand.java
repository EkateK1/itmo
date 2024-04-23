package org.example.commands.available;

import org.example.commands.abstracts.Command;
import org.example.managers.CollectionManager;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.Status;

import java.util.ArrayList;
import java.util.Map;

public class PrintDescendingCommand extends Command {
    private CollectionManager collectionManager;


    public PrintDescendingCommand(CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request){
        StringBuilder result = new StringBuilder();
        ArrayList<LabWork> list = new ArrayList<LabWork>();
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            list.add(entry.getValue());
        }
        for (int i = list.size()-1; i >= 0 ; i--){
            result.append(list.get(i));
        }
        return new Response(Status.OK, result.toString());
    }
}
