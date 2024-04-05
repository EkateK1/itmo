package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Discipline;

public class DisciplineCreating {

    public static Discipline disciplineCreating(ConsoleManager console) {

        String name;
        while (true){
            console.println("Введите название дисциплины");
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
                    console.printError("Название дисциплины должно содержать буквы");
                }
            }
        }
        Long practiceHours;
        while (true){
            console.println("Введите часы практики");
            var line = "";
            if (console.getFileMode()){
                line = console.getScanner().nextLine().trim();
            } else {
                line = console.readln().trim();
            }
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()){
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    practiceHours = Long.parseLong(line);
                    if (console.getFileMode()) {
                        console.println(practiceHours);
                    }
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
