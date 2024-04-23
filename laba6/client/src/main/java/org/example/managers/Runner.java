package org.example.managers;

import org.example.exceptions.ScriptRecursionException;
import org.example.network.UDPClient;
import org.example.creating.LabWorkCreating;
import org.example.exceptions.NotFoundCommandException;
import org.example.model.LabWork;
import org.example.network.Request;
import org.example.network.Response;
import org.example.utility.ConsoleManager;
import org.example.utility.Status;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    ConsoleManager console;
    UDPClient client;
    ArrayList<String> usedFileNames = new ArrayList<>();
    boolean running = true;

    public Runner(ConsoleManager console, UDPClient client){
        this.console = console;
        this.client = client;
    }

    public void commandGetting(){
        try{

            String[] userCommand;

            while (running){
                userCommand = (console.readln().trim()+" ").split(" ", 2);
                if (userCommand[0].isEmpty()) throw new NotFoundCommandException();

                client.sendData(new Request (userCommand[0].trim(), userCommand[1].trim()));
                Response response = client.readData();

                switch (response.getStatus()){
                    case ASK_LABWORK -> {

                        LabWork labWork = LabWorkCreating.labWorkCreating(console);

                        client.sendData(new Request (userCommand[0].trim(), userCommand[1].trim(), labWork));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK){
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case EXIT -> {
                        running = false;
                        console.printError(response.getMessage());
                        System.exit(1);
                    }
                    case EXECUTE_SCRIPT -> {
                        console.setFileMode(true);
                        scriptLaunch(userCommand);
                    }
                    case ASK_ID -> {
                        Integer id = LabWorkCreating.labWorkSetId(console);

                        client.sendData(new Request(userCommand[0].trim(), id.toString()));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK){
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    default -> {
                        printResponse(response);
                    }
                }
            }


        }catch(NoSuchElementException e){
            console.printError("Пользовательский ввод не обнаружен");
        }catch (IllegalStateException e){
            console.printError("Непредвиденная ошибка");
        }catch (NotFoundCommandException e){
            console.printError(e.getMessage());
        } catch (IOException e) {
           console.printError("Неизвестная ошибка " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void scriptLaunch(String[] userCommand){
        boolean commandStatus = true;
        if (userCommand[1].isEmpty()) {
            console.printError("Введите название файла со скриптом");
            return;
        }
        String fileName = userCommand[1];
        try{
            usedFileNames.add(fileName);
            String line;
            String[] scriptCommand = {" ", " "};
            File file = new File(fileName);
            console.setFileMode(true);
            console.setScanner(new Scanner(file));
            while (commandStatus && console.getScanner().hasNext() && (line = console.getScanner().nextLine()) != null){
                scriptCommand = (line.trim()+" ").split(" ", 2);
                scriptCommand[1] = scriptCommand[1].trim();
                while (scriptCommand[0].isEmpty() && console.getScanner().nextLine() != null){
                    scriptCommand = (line.trim()+" ").split(" ", 2);
                    scriptCommand[1] = scriptCommand[1].trim();
                }
                if (scriptCommand[0].equals("execute_script")){
                    if (usedFileNames.contains(fileName)) throw new ScriptRecursionException();
                }
                console.println("Выполнение команды " + scriptCommand[0]);
                client.sendData(new Request(scriptCommand[0].trim(), scriptCommand[1].trim()));
                Response response = client.readData();

                switch (response.getStatus()){
                    case ASK_LABWORK -> {
                        LabWork labWork = LabWorkCreating.labWorkCreating(console);

                        client.sendData(new Request(scriptCommand[0].trim(), scriptCommand[1].trim(), labWork));
                        Response newResponse = client.readData();

                        if (newResponse.getStatus() != Status.OK){
                            console.printError(newResponse.getMessage());
                        } else {
                            printResponse(newResponse);
                        }
                    }
                    case EXIT -> {
                        running = false;
                    }
                    case EXECUTE_SCRIPT -> {
                        console.setFileMode(true);
                        scriptLaunch(scriptCommand);
                    }
                    default -> {
                        printResponse(response);
                    }
                }

            }
        } catch (ScriptRecursionException e){
            console.printError(e.getMessage());
        } catch(IOException e){
            console.printError("Ошибка чтения файла");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        console.setFileMode(false);
        usedFileNames.remove(fileName);
    }

    public void printResponse(Response response) {
        switch (response.getStatus()) {
            case OK -> console.println(response.getMessage());
            case ERROR -> console.printError(response.getMessage());
            case WRONG_ARGUMENT -> console.printError(response.getMessage());
            default -> {
            }
        }
    }

}

