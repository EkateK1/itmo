package org.example.managers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.LabWork;
import org.example.utility.Console;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DumpManager {

    String fileName;
    Console console;
    //ConsoleManager
    private final Gson gson = new Gson();
    private final Logger logger = Logger.getLogger("UDPServerLogger");
    public DumpManager(String fileName, Console console){
        this.fileName = fileName;
        this.console = console;
    }

    public boolean writeIntoFile(TreeMap<Integer, LabWork> collection){
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(gson.toJson(collection));
            fileWriter.flush();
            logger.log(Level.INFO, "Коллекция успешно загружена в файл " + fileName);
            return true;
        }catch (IOException e){
            logger.log(Level.SEVERE, "Загрузочный файл не может быть открыт");
            return false;
        }
    }

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
                    logger.log(Level.INFO, "Файл " + fileName + " пуст");
                    TreeMap<Integer, LabWork> collection = new TreeMap<>();
                    return collection;
                }

                Type type = new TypeToken<TreeMap<Integer, LabWork>>(){}.getType();
                TreeMap<Integer, LabWork> collection = gson.fromJson(jsonString.toString(), type);
                for (Map.Entry<Integer, LabWork> entry : collection.entrySet()){
                    if (!entry.getValue().validate()){
                        //console.printError("Введенные данные невалидны, проверьте файл");
                        collection.clear();
                        return collection;
                    }
                }
                logger.log(Level.INFO, "Коллекция из файла " + fileName + " успешно загружена");
                return collection;

            }catch(IOException e){
                logger.log(Level.SEVERE, "Ошибка чтения файла");
            }
        }else{
            logger.log(Level.WARNING, "Название файла указано некорректно");
        }
        return new TreeMap<>();
    }

}
