package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private static final String MODEL_TECHNICAL_ERROR = "technicalError";
    private static final String MODEL_USER_TOTAL = "userTotal";
    private static final String USER_TOTAL_MESSAGE = "userTotalMessage";
    private static final String USER_TOTAL_IN_USD_MESSAGE = "User Total: %s$";
    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage;
        try {
            totalMessage = getUserTotalMessage(userId);
        } catch (DatabaseConnectionException e) {
            return MODEL_TECHNICAL_ERROR;
        }

        model.addAttribute(USER_TOTAL_MESSAGE, totalMessage);
        return MODEL_USER_TOTAL;
    }

    private String getUserTotalMessage(String userId) throws DatabaseConnectionException {
        Double amount;
        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (UserException e) {
            return e.getDescription();
        }
        return String.format(USER_TOTAL_IN_USD_MESSAGE, amount);
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
