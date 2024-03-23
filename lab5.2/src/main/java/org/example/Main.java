package org.example;

import org.example.commands.*;
import org.example.managers.*;

public class Main {

    public static void main(String[] args) {

        var console = new ConsoleManager();
        DumpManager dumpManager = new DumpManager("example.json", console);
        CollectionManager collectionManager = new CollectionManager(dumpManager);
        collectionManager.loadCollection(dumpManager);

        CommandManager commandManager = new CommandManager(){{
            registration("help", new HelpCommand(console, this));
            registration("info", new InfoCommand(console,collectionManager));
            registration("show", new ShowCommand(console, collectionManager));
            registration("insert", new InsertCommand(console, collectionManager));
            registration("update", new UpdateCommand(console, collectionManager));
            registration("remove_key", new RemoveKeyCommand(console, collectionManager));
            registration("clear", new ClearCommand(collectionManager));
            registration("save", new SaveCommand(dumpManager, collectionManager));
            registration("execute_script", new ExecuteScriptCommand(dumpManager, collectionManager, console));
            registration("exit", new ExitCommand(console));
            registration("remove_greater", new RemoveGreaterCommand(console, collectionManager));
            registration("remove_lower", new RemoveLowerCommand(console, collectionManager));
            registration("remove_lower_key", new RemoveLowerKeyCommand(console, collectionManager));
            registration("average_of_maximum_point", new AverageOfMaximumPointCommand(console, collectionManager));
            registration("min_by_name", new MinByNameCommand(console, collectionManager));
            registration("print_descending", new PrintDescendingCommand(console, collectionManager));
            //registration("add", new AddCommand(console, collectionManager));
        }};
        Runner runner = new Runner(console, commandManager);
        runner.commandGetting();


    }
}