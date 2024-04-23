package org.example.exceptions;

import java.io.IOException;

public class ElementAddingException extends IOException {
    public String getMessage(){
        return "Произошла ошибка при добавлении элемента";
    }
}
