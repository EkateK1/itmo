package org.example.creating;

import org.example.utility.ConsoleManager;
import org.example.model.Coordinates;
import org.example.model.Difficulty;
import org.example.model.Discipline;
import org.example.model.LabWork;

public class LabWorkCreating {

    public static Integer labWorkSetId(ConsoleManager console){

        Integer id;
        while(true){
            console.println("Введите id лабораторной работы");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    id = Integer.parseInt(line);
                    if (id<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        return id;
    }


    public static LabWork labWorkCreating(ConsoleManager console){

        String name;
        while (true){
            console.println("Введите название лабораторной работы");
            if (console.getFileMode()){
                name = console.getScanner().nextLine().trim();
            } else {
                name = console.readln().trim();
            }
            if (name.equals("exit")) System.exit(1);
            if (console.getFileMode()) {
                console.println(name);
            }
            if (name.isEmpty()){
                console.printError("Значение не было введено");
            } else{
                if (!name.matches("^[0-9]+$")){
                    break;
                }else{
                    console.printError("Название лабораторной работы должно содержать буквы");
                }
            }
        }
        Coordinates coordinates = CoordinatesCreating.coordinatesCreating(console);
        float minimalPoint;
        while (true){
            console.println("Введите минимальный балл");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    minimalPoint = Float.parseFloat(line);
                    if (console.getFileMode()) {
                        console.println(minimalPoint);
                    }
                    if (minimalPoint<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        double maximumPoint;
        while (true){
            console.println("Введите максимальный балл");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    maximumPoint = Double.parseDouble(line);
                    if (console.getFileMode()) {
                        console.println(maximumPoint);
                    }
                    if (maximumPoint < minimalPoint){
                        console.printError("Значение должно быть не меньше минимума баллов");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        Float personalQualitiesMinimum;
        while (true){
            console.println("Введите минимум личных качеств");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    personalQualitiesMinimum = Float.parseFloat(line);
                    if (console.getFileMode()) {
                        console.println(personalQualitiesMinimum);
                    }
                    if (personalQualitiesMinimum<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        Difficulty difficulty;
        while (true){
            console.println("Введите сложность, выбрав из перечисленных: " + Difficulty.names());
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try {
                    difficulty = Difficulty.valueOf(line);
                    if (console.getFileMode()) {
                        console.println(difficulty);
                    }
                    break;
                } catch (NullPointerException | IllegalArgumentException  e) {
                    console.printError("Введенное значение должно быть выбрано из списка: " + Difficulty.names());
                }
            }
        }
        Discipline discipline = DisciplineCreating.disciplineCreating(console);
        return new LabWork(name, coordinates, minimalPoint, maximumPoint, personalQualitiesMinimum, difficulty, discipline);

    }
}
