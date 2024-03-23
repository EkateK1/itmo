package org.example.commands;

import org.example.creating.LabWorkCreating;
import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;

public class InsertCommand extends Command{
    // сам генерирует значени

    private ConsoleManager console;
    private CollectionManager collectionManager;

    public InsertCommand(ConsoleManager console, CollectionManager collectionManager) {
        super("insert", "добавить новый элемент с заданным ключом");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите ключ для добавления элемента");
            return false;
        }
        if (!argument.matches("\\d+")){
            console.printError("Ключ должен быть числом");
            return false;
        }
        Integer key = Integer.parseInt(argument);
        if (collectionManager.getCollectionById(key) != null ){
            console.printError("Значение с таким ключом уже существует");
            return false;
        }
        console.println("Создайте лабораторную работу, которую нужно добавить в коллекцию");

        if (collectionManager.addById(key, LabWorkCreating.labWorkCreating(console))) return true;
        console.printError("Произошла ошибка при добавлении элемента");
        return false;
    }
}
