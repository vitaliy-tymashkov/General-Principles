package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;
import org.junit.Assert;
import org.junit.Test;

public class MortgageInstallmentCalculatorUtilityTest {
    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountsAreSmall() throws InvalidInputException {
        double monthlyPaymentAmount = MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(1000, 1, 12);
        Assert.assertEquals(88.84d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenAmountIsLarge() throws InvalidInputException {
        double monthlyPaymentAmount = MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(10000000, 1, 12);
        Assert.assertEquals(888487.88d, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenPrincipalIsZero() throws InvalidInputException {
        double monthlyPaymentAmount = MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(0, 1, 12);
        Assert.assertEquals(0, monthlyPaymentAmount, 0.01d);
    }

    @Test
    public void shouldCalculateMonthlyPaymentWhenInterestRateIsZero() throws InvalidInputException {
        double monthlyPaymentAmount = MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(1000, 1, 0);
        Assert.assertEquals(83.33, monthlyPaymentAmount, 0.01d);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeTenure() throws InvalidInputException {
        MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(20, -10, 14.5);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativeInterestRate() throws InvalidInputException {
        MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(20, 1, -12);
    }

    @Test(expected = InvalidInputException.class)
    public void shouldThrowInvalidInputExceptionOnNegativePrincipalAmount() throws InvalidInputException {
        MortgageInstallmentCalculatorUtility.calculateMonthlyPayment(-20, 10, 14.5);
    }
}
