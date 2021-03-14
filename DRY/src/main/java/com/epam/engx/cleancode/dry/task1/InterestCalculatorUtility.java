package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class InterestCalculatorUtility {

    private static final int PERCENT_BASE = 100;
    private static final int SENIOR_AGE = 60;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;
    public static final int YEAR_CORRECTION = 1;

    private InterestCalculatorUtility() {
        throw new UnsupportedOperationException();
    }

    public static boolean isSeniorAge(int ageOfClient) {
        return ageOfClient >= SENIOR_AGE;
    }

    public static BigDecimal getInterest(AccountDetails accountDetails, double annualInterestRate) {
        Date now = new Date();
        return BigDecimal.valueOf((accountDetails.getBalance().doubleValue()
                * durationBetweenDatesInYears(accountDetails.getStartDate(), now)
                * annualInterestRate)
                / PERCENT_BASE);
    }

    public static boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private static int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);

        int differenceOfYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return getDifferenceInYear(startCalendar, endCalendar, differenceOfYears);
    }

    private static int getDifferenceInYear(Calendar startCalendar, Calendar endCalendar, int differenceInYears) {
        if (isStartDayHigherThanEndDay(startCalendar.get(Calendar.DAY_OF_YEAR), endCalendar.get(Calendar.DAY_OF_YEAR))) {
            return differenceInYears - YEAR_CORRECTION;
        } else {
            return differenceInYears;
        }
    }

    private static boolean isStartDayHigherThanEndDay(int dayOfStartYear, int dayOfEndYear) {
        return dayOfStartYear > (dayOfEndYear + LEAP_YEAR_SHIFT);
    }
}
