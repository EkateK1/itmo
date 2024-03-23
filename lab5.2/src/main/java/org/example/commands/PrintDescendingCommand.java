package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.models.LabWork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class PrintDescendingCommand extends Command{

    private ConsoleManager console;
    private CollectionManager collectionManager;


    public PrintDescendingCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("print_descending", "вывести элементы коллекции в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        ArrayList <LabWork> list = new ArrayList<LabWork>();
        for (Map.Entry<Integer, LabWork> entry : collectionManager.getCollection().entrySet()){
            list.add(entry.getValue());
        }
        for (int i = list.size()-1; i >= 0 ; i--){
            console.println(list.get(i));
        }
        return true;
    }
}
