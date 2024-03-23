package org.example.models;

public class Coordinates {

    private double x;
    private double y;

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return (x + ";" + y);
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
        return x == other.x && y == other.y;

    }
}
