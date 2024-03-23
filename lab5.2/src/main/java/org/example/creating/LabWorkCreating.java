package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Coordinates;
import org.example.models.Difficulty;
import org.example.models.Discipline;
import org.example.models.LabWork;

public class LabWorkCreating {

    public static Integer labWorkSetId(ConsoleManager console){

        Integer id;
        while(true){
            console.println("Введите id лабораторной работы");
            var line = console.readln().trim();
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
            name = console.readln().trim();
            if (name.equals("exit")) System.exit(1);
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
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    minimalPoint = Float.parseFloat(line);
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
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    maximumPoint = Double.parseDouble(line);
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
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    personalQualitiesMinimum = Float.parseFloat(line);
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
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try {
                    difficulty = Difficulty.valueOf(line);
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
