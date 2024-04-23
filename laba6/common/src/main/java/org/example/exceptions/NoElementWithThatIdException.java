package org.example.exceptions;

import java.io.IOException;

public class NoElementWithThatIdException extends IOException {
    public String getMessage(){
        return "Элемента с таким id не существует";
    }
}
