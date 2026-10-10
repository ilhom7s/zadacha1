package org.example.exceptions;

public class FileExceptions extends RuntimeException{
    public FileExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
