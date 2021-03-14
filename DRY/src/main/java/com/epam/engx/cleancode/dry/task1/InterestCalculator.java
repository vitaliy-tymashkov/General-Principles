package com.epam.engx.cleancode.dry.task1;

import static com.epam.engx.cleancode.dry.task1.InterestCalculatorUtility.*;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;

public class InterestCalculator implements Profitable {

    private static final double SENIOR_PERCENT = 5.5d;
    private static final double COMMON_PERCENT = 4.5d;

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
}
