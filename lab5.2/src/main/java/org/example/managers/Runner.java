package org.example.managers;

import org.example.commands.Command;

import java.util.NoSuchElementException;


public class Runner {

    ConsoleManager console;
    CommandManager commandManager;

   public Runner(ConsoleManager console, CommandManager commandManager){
       this.console = console;
       this.commandManager = commandManager;
   }

   public void commandGetting(){
       try{

           String[] userCommand;

           while (true){
                userCommand = (console.readln().trim()+" ").split(" ", 2);
                if (userCommand[0].equals("")) {console.printError("Команда не введена");
                } else {
                    if (commandManager.getCommand(userCommand[0]) == null) {
                        console.printError("Команда введена некорректно");
                    } else {
                        userCommand[1] = userCommand[1].trim();

                        if (userCommand[0].equals("exit")) break;
                        boolean commandStatus = commandLaunch(userCommand);

                        if (commandStatus) console.println("Команда выполнена успешно");}}
           }

       }catch(NoSuchElementException e){
           console.printError("Пользовательский ввод не обнаружен");
       }catch (IllegalStateException e){
           console.printError("Непредвиденная ошибка");
       }
   }

    public boolean commandLaunch(String[] userCommand) {
        Command command = commandManager.getCommand(userCommand[0]);

        switch (userCommand[0]){

            case("execute_script") -> {return true;}
            default -> {
                return command.execute(userCommand[1]);
            }
        }
    }

}
