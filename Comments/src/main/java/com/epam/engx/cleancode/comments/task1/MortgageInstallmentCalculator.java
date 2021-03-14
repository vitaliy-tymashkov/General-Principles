package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int MINIMUM_VALUE = 0;
    private static final int MONTHS_IN_YEAR = 12;
    private static final double RATE_OF_INTEREST_IN_PERCENT_BASE = 100.0D;

    public static double calculateMonthlyPayment(
            int principalAmount, int termOfMortgageInYears, double rateOfInterestInPercent) {

        checkForNegativeValues(principalAmount, termOfMortgageInYears, rateOfInterestInPercent);

        double termInMonths = getTermInMonths(termOfMortgageInYears);
        double rateOfInterestDecimalValue = getRateOfInterestDecimalValue(rateOfInterestInPercent);
        double monthlyRate = getMonthlyRate(rateOfInterestDecimalValue);

        //for zero interest rates
        if(rateOfInterestDecimalValue==0) {
            return  principalAmount/termInMonths;
        }


        return getMonthlyPayment(principalAmount, termInMonths, monthlyRate);
    }

    private static double getRateOfInterestDecimalValue(double rateOfInterestInPercent) {
        return rateOfInterestInPercent / RATE_OF_INTEREST_IN_PERCENT_BASE;
    }

    private static int getTermInMonths(int termOfMortgageInYears) {
        return termOfMortgageInYears * MONTHS_IN_YEAR;
    }

    private static double getMonthlyRate(double rateOfInterest) {
        return rateOfInterest / (double) MONTHS_IN_YEAR;
    }

    private static void checkForNegativeValues(int principalAmount, int termOfMortgageInYears, double rateOfInterest) {
        if (principalAmount < MINIMUM_VALUE || termOfMortgageInYears <= MINIMUM_VALUE || rateOfInterest < MINIMUM_VALUE) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }

    private static double getMonthlyPayment(int principalAmount, double termInMonths, double monthlyRate) {
        double totalAmount = principalAmount * monthlyRate;
        double numberOfPayments = 1 - Math.pow(1 + monthlyRate, -termInMonths);

        return totalAmount / numberOfPayments;
    }
}
