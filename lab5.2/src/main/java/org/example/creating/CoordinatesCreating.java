package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Coordinates;

public class CoordinatesCreating {
    public static Coordinates coordinatesCreating(ConsoleManager console) {
        double x;
        double y;
        while (true){
            console.println("Введите координату x");
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    x = Double.parseDouble(line);
                    break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        while (true){
            console.println("Введите координату y");
            var line = console.readln().trim();
            if (line.equals("exit")) System.exit(1);
            if (line.isEmpty()) {
                console.printError("Значение не было введено");
            }
            if (!line.isEmpty()){
                try{
                    y = Double.parseDouble(line);
                    break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        return new Coordinates(x,y);
    }
}
