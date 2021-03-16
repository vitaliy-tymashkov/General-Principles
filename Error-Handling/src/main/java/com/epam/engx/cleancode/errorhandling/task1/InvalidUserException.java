package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidUserException extends UserException {

    protected InvalidUserException(ErrorStatus errorEnum) {
        super(errorEnum);
    }
}
