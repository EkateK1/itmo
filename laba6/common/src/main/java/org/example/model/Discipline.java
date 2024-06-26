package org.example.model;

import org.example.utility.Validatable;

import java.io.Serializable;

public class Discipline implements Validatable, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long practiceHours; //Поле не может быть null

    public  Discipline(String name, Long practiceHours){
        this.name = name;
        this.practiceHours = practiceHours;
    }

    @Override
    public String toString() {
        return  '\t' + "name = " + name + '\n' +
                '\t' + "practiceHours = " + practiceHours + '\n' +
                '}';
    }

    @Override
    public boolean validate(){
        if (name == null || name.isEmpty()) return false;
        if (practiceHours == null) return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Discipline other = (Discipline) obj;
        return name.equals(other.name) &&
                practiceHours.equals(other.practiceHours);

    }
}
