package org.example.commands;

import org.example.managers.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExecuteScriptCommand extends Command{

    private DumpManager dumpManager;
    private CollectionManager collectionManager;
    private ConsoleManager console;
    private CommandManager commandManager;
    private Runner runner;


    public ExecuteScriptCommand(DumpManager dumpManager, CollectionManager collectionManager, ConsoleManager console) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
        this.console = console;
    }

    public boolean execute(String argument){
        return true;
    }
}
