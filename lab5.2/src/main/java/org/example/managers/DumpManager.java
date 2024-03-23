package org.example.managers;

import com.google.gson.reflect.TypeToken;
import org.example.models.LabWork;


import java.io.*;
import java.lang.reflect.Type;
import java.util.TreeMap;

import com.google.gson.Gson;

public class DumpManager {

    String fileName;
    ConsoleManager console;
    private Gson gson = new Gson();
    public DumpManager(String fileName, ConsoleManager console){
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */

    public boolean writeIntoFile(TreeMap<Integer, LabWork> collection){
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(gson.toJson(collection));
            fileWriter.flush();
            console.println("Коллекция успешно загружена в файл " + fileName);
            return true;
        }catch (IOException e){
            console.printError("Загрузочный файл не может быть открыт");
            return false;
        }
    }

    /**
     * Считывает коллекцию из файла.
     * @return Считанная коллекция
     */

    public TreeMap<Integer, LabWork> readFromFile(){
        if (fileName != null && !fileName.isEmpty()){
            try(FileInputStream fileInput = new FileInputStream(fileName);
                InputStreamReader inputReader = new InputStreamReader(fileInput);
                BufferedReader reader = new BufferedReader(inputReader);)
            {
                StringBuilder jsonString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (!line.isEmpty()){
                        jsonString.append(line);
                    }
                }

                if (jsonString.isEmpty()){
                    console.printError("Файл пуст");
                    TreeMap<Integer, LabWork> collection = new TreeMap<Integer, LabWork>();
                    return collection;
                }

                Type type = new TypeToken<TreeMap<Integer, LabWork>>(){}.getType();
                TreeMap<Integer, LabWork> collection = gson.fromJson(jsonString.toString(), type);
                console.println("Коллекция успешно загружена");
                return collection;

            }catch(IOException e){
                console.printError("Ошибка чтения файла");
            }
        }else{
            console.printError("Название файла указано некорректно");
        }
        return new TreeMap<>();
    }

}
