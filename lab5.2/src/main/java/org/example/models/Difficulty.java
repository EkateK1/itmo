package org.example.models;

public enum Difficulty {
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
