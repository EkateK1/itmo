package org.example.exceptions;

import java.io.IOException;

public class IllegalKeyException extends IOException {
    public String getMessage(){
        return "Ключ должен быть числом";
    }
}
