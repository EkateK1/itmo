package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.managers.ConsoleManager;
import org.example.managers.DumpManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteScriptCommand extends Command{

    private DumpManager dumpManager;
    private CollectionManager collectionManager;
    private ConsoleManager console;

    public ExecuteScriptCommand(DumpManager dumpManager, CollectionManager collectionManager, ConsoleManager console) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.dumpManager = dumpManager;
        this.collectionManager = collectionManager;
        this.console = console;
    }

    public boolean execute(String argument){
        if (argument.isEmpty()) {
            console.printError("Введите файл со скриптом");
            return false;
        }
        String fileName = argument;
        try(FileInputStream fileInput = new FileInputStream(fileName);
            InputStreamReader inputReader = new InputStreamReader(fileInput);
            BufferedReader reader = new BufferedReader(inputReader);)
            {
                String line;
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (!line.isEmpty()){

                    }
                }
            }catch(IOException e){
            console.printError("Ошибка чтения файла");
        }
        return true;}
}
