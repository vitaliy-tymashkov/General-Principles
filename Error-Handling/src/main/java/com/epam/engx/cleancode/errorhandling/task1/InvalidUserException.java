package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidUserException extends UserException {

    public InvalidUserException(ERROR_ENUM errorEnum) {
        super.setErrorEnum(errorEnum);
        super.setDescription(errorEnum.getDescription());
    }
}
