package org.example.models;

import org.example.managers.CollectionManager;
import org.example.utility.Element;
import org.example.utility.Validatable;
import static java.lang.Math.*;

import java.util.Date;
import java.util.Objects;

public class LabWork extends Element implements Validatable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float minimalPoint; //Значение поля должно быть больше 0
    private double maximumPoint; //Значение поля должно быть больше 0
    private Float personalQualitiesMinimum; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Discipline discipline; //Поле не может быть null
    private static Integer nextId = 1;
    public LabWork(String name, Coordinates coordinates, float minimalPoint, double maximumPoint,
                   Float personalQualitiesMinimum, Difficulty difficulty, Discipline discipline){
        this.id = nextId;
        nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.maximumPoint = maximumPoint;
        this.personalQualitiesMinimum = personalQualitiesMinimum;
        this.difficulty = difficulty;
        this.discipline = discipline;
        this.creationDate = new Date();
    }
    @Override
    public String toString() {
        return "LabWork{" + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "coordinates { " + '\n' + coordinates + '\n' +
                "creationDate = " + creationDate + '\n' +
                "minimalPoint = " + minimalPoint + '\n' +
                "maximumPoint = " + maximumPoint + '\n' +
                "personalQualitiesMinimum = " + personalQualitiesMinimum + '\n' +
                "difficulty = " + difficulty + '\n' +
                "discipline { " + '\n' + discipline + '\n' +
                '}' + '\n';
    }

    @Override
    public boolean validate(){
        if (id == null || id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (minimalPoint <= 0) return false;
        if (maximumPoint <= 0) return false;
        if (personalQualitiesMinimum == null || personalQualitiesMinimum <=0) return false;
        if (difficulty == null) return false;
        if (discipline == null) return false;
        return true;
    }
    @Override
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
        nextId = max(nextId, id + 1);
    }
    public double getMaximumPoint(){return maximumPoint;}
    public String getName(){return name;}

    public void updateNextId(CollectionManager collectionManager){

        Integer maxId = collectionManager.getMaxId();
        nextId = maxId + 1;

    }

    @Override
    public int compareTo(Element element){
        return (id - element.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, minimalPoint, maximumPoint,
                personalQualitiesMinimum, difficulty, discipline);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        LabWork other = (LabWork) obj;
        return id.equals(other.id) &&
                name.equals(other.name) &&
                coordinates.equals(other.coordinates) &&
                creationDate.equals(other.creationDate) &&
                minimalPoint == other.minimalPoint &&
                maximumPoint == other.maximumPoint &&
                personalQualitiesMinimum.equals(other.personalQualitiesMinimum) &&
                difficulty.equals(other.difficulty) &&
                discipline.equals(other.discipline);

    }

}
