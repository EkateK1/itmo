package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Discipline;

public class DisciplineCreating {

    public static Discipline disciplineCreating(ConsoleManager console) {

        String name;
        while (true){
            console.println("Введите название дисциплины");
            name = console.readln().trim();
            if (name.equals("exit")) System.exit(1);
            if (name.isEmpty()){
                console.printError("Значение не было введено");
            } else{
                if (!name.matches("^[0-9]+$")){
                    break;
                }else{
                    console.printError("Название дисциплины должно содержать буквы");
                }
            }
        }
        Long practiceHours;
        while (true){
            console.println("Введите часы практики");
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()){
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    practiceHours = Long.parseLong(line);
                    if (practiceHours<=0){
                        console.printError("Значение должно быть больше нуля");
                    } else{
                        break;
                    }
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        return new Discipline(name, practiceHours);

    }
}
