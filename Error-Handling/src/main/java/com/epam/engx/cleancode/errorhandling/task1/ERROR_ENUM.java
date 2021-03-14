package com.epam.engx.cleancode.errorhandling.task1;

public enum ERROR_ENUM {
    INVALID_USER(-1.0D, "WARNING: User ID doesn't exist."),
    EMPTY_ORDERS(-2.0D, "WARNING: User have no submitted orders."),
    ORDER_TOTAL_LESS_THAN_ZERO(-3.0D, "ERROR: Wrong order amount.");

    private double numVal;
    private String description;

    ERROR_ENUM(double numVal, String description) {
        this.numVal = numVal;
        this.description = description;
    }

    public double getNumVal() {
        return numVal;
    }

    public void setNumVal(double numVal) {
        this.numVal = numVal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
