package org.example.creating;

import org.example.managers.ConsoleManager;
import org.example.models.Coordinates;

public class CoordinatesCreating {
    public static Coordinates coordinatesCreating(ConsoleManager console) {
        double x;
        while (true){
            console.println("Введите координату x");
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
                    x = Double.parseDouble(line);
                    if (console.getFileMode()) {
                        console.println(x);
                    }
                    if (x < -200 || x > 120){
                        console.printError("Значение должно находить в диапазоне от -200 до 120");
                    } else break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");

                }
            }
        }
        Long y;
        while (true){
            console.println("Введите координату y");
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
                    y = Long.parseLong(line);
                    if (console.getFileMode()) {
                        console.println(y);
                    }
                    if (y < -5 || y > 100){
                        console.printError("Значение должно находить в диапазоне от -5 до 100");
                    } else break;
                } catch (NumberFormatException e){
                    console.printError("Значение должно быть числом");
                }
            }
        }
        return new Coordinates(x,y);
    }
}
