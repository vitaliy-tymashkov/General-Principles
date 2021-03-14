package com.epam.engx.cleancode.errorhandling.task1;

public abstract class UserException extends RuntimeException {
    private ERROR_ENUM errorEnum;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ERROR_ENUM getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ERROR_ENUM errorEnum) {
        this.errorEnum = errorEnum;
    }
}
