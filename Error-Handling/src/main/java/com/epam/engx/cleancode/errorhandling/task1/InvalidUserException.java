package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidUserException extends UserException {

    protected InvalidUserException(ERROR_ENUM errorEnum) {
        super(errorEnum);
    }
}
