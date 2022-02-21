package ru.job4j.exception;

public class EmptyArgumentException extends RuntimeException {

    public EmptyArgumentException() {
    }

    public EmptyArgumentException(String message) {
        super(message);
    }
}
