package com.epam.engx.cleancode.errorhandling.task1;

public abstract class UserException extends RuntimeException {
    private ErrorStatus errorEnum;
    private String description;

    public UserException(ErrorStatus errorEnum) {
        this.errorEnum = errorEnum;
        this.description = errorEnum.getDescription();
    }

    public String getDescription() {
        return description;
    }
}
