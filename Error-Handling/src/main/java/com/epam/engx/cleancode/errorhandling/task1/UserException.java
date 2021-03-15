package com.epam.engx.cleancode.errorhandling.task1;

public abstract class UserException extends RuntimeException {
    private ERROR_ENUM errorEnum;
    private String description;

    public UserException(ERROR_ENUM errorEnum) {
        this.errorEnum = errorEnum;
        this.description = errorEnum.getDescription();
    }

    public String getDescription() {
        return description;
    }
}
