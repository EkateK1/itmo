package org.example.model;

import java.io.Serializable;

public enum Difficulty implements Serializable {
    VERY_EASY,
    VERY_HARD,
    INSANE,
    TERRIBLE;

    public static String names(){
        StringBuilder nameList = new StringBuilder();
        for (var difficulty: values()){
            nameList.append(difficulty.name()).append(", ");
        }
        return (nameList).substring(0, nameList.length()-2);
    }
}
