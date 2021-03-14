package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Model;

public class UserReportController {

    private UserReportBuilder userReportBuilder;

    public String getUserTotalOrderAmountView(String userId, Model model) {
        String totalMessage;
        try {
            totalMessage = getUserTotalMessage(userId);
        } catch (DatabaseConnectionException e) {
            return "technicalError";
        }

        model.addAttribute("userTotalMessage", totalMessage);
        return "userTotal";
    }

    private String getUserTotalMessage(String userId) throws DatabaseConnectionException {
        Double amount;
        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (UserException e) {
            return e.getDescription();
        }
        return "User Total: " + amount + "$";
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
