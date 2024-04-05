package org.example.models;

public class Coordinates {

    private double x; //от -200 до 120
    private Long y; // от -5 до 100

    public Coordinates(double x, Long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return  '\t' + "x = " + x + '\n' +
                '\t' + "y = " + y + '\n' +
                "}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Coordinates other = (Coordinates) obj;
        return x == other.x && y.equals(other.y);

    }
}
