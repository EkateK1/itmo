package org.example;

import org.example.commands.available.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.DumpManager;
import org.example.utility.ConsoleManager;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Logger;

public class Server {
    public static final int PORT = 23516;
    private static final ConsoleManager console = new ConsoleManager();

    public static void main(String[] args) {

        DumpManager dumpManager = new DumpManager("example.json", console);
        CollectionManager collectionManager = new CollectionManager(dumpManager);
        collectionManager.loadCollection(dumpManager);
        Logger logger = Logger.getLogger("UDPServerLogger");

        CommandManager commandManager = new CommandManager(){{
            registration("help", new HelpCommand(this));
            registration("info", new InfoCommand(collectionManager));
            registration("show", new ShowCommand(collectionManager));
            registration("insert", new InsertCommand(collectionManager));
            registration("update", new UpdateCommand(collectionManager));
            registration("remove_key", new RemoveKeyCommand(collectionManager));
            registration("clear", new ClearCommand(collectionManager));
            registration("save", new SaveCommand(dumpManager, collectionManager));
            registration("execute_script", new ExecuteScriptCommand(dumpManager, collectionManager));
            registration("exit", new ExitCommand(collectionManager, dumpManager));
            registration("remove_greater", new RemoveGreaterCommand(collectionManager));
            registration("remove_lower", new RemoveLowerCommand(collectionManager));
            registration("remove_lower_key", new RemoveLowerKeyCommand(collectionManager));
            registration("average_of_maximum_point", new AverageOfMaximumPointCommand(collectionManager));
            registration("min_by_name", new MinByNameCommand(collectionManager));
            registration("print_descending", new PrintDescendingCommand(collectionManager));
            registration("add", new AddCommand(collectionManager));
        }};

        try{
            UDPServer server = new UDPServer(InetAddress.getLocalHost(), PORT, logger, console, commandManager);
            server.run();
            collectionManager.saveCollection(dumpManager);
        }catch (IOException e){
            console.printError("При подключении что-то пошло не так" + e.getMessage());
        }

    }
}