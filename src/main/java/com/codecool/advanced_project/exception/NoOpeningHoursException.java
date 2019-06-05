package com.codecool.advanced_project.exception;

public class NoOpeningHoursException extends RuntimeException {
    public NoOpeningHoursException() {
        super("There is no opening hours");
    }
}
