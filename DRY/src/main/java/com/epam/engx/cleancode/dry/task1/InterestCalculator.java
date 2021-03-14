package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int PERCENT_BASE = 100;
    private static final int SENIOR_AGE = 60;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final double COMMON_PERCENT = 4.5d;
    public static final int YEAR_CORRECTION = 1;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        BigDecimal interest;
        if (isSeniorAge(accountDetails.getAge())) {
            interest = getInterest(accountDetails, SENIOR_PERCENT);
        } else {
            interest = getInterest(accountDetails, COMMON_PERCENT);
        }
        return interest;
    }

    private boolean isSeniorAge(int ageOfClient) {
        return ageOfClient >= SENIOR_AGE;
    }

    private BigDecimal getInterest(AccountDetails accountDetails, double annualInterestRate) {
        Date now = new Date();
        return BigDecimal.valueOf((accountDetails.getBalance().doubleValue()
                * durationBetweenDatesInYears(accountDetails.getStartDate(), now)
                * annualInterestRate)
                / PERCENT_BASE);
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(from);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(to);

        int differenceOfYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        return getDifferenceInYear(startCalendar, endCalendar, differenceOfYears);
    }

    private int getDifferenceInYear(Calendar startCalendar, Calendar endCalendar, int differenceInYears) {
        if (isStartDayHigher(startCalendar.get(Calendar.DAY_OF_YEAR), endCalendar.get(Calendar.DAY_OF_YEAR))) {
            return differenceInYears - YEAR_CORRECTION;
        } else {
            return differenceInYears;
        }
    }

    private boolean isStartDayHigher(int dayOfStartYear, int dayOfEndYear) {
        return dayOfStartYear > (dayOfEndYear + LEAP_YEAR_SHIFT);
    }
}
