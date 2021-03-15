package com.epam.engx.cleancode.errorhandling.task1;

public enum ERROR_ENUM {
    INVALID_USER("WARNING: User ID doesn't exist."),
    EMPTY_ORDERS("WARNING: User have no submitted orders."),
    ORDER_TOTAL_LESS_THAN_ZERO("ERROR: Wrong order amount.");

    private final String description;

    ERROR_ENUM(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
