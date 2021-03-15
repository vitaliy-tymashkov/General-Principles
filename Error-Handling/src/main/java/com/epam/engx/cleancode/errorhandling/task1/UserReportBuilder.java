package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

import static com.epam.engx.cleancode.errorhandling.task1.ERROR_ENUM.*;

public class UserReportBuilder {

    private static final int MINIMUM_VALUE = 0;
    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId) {
        checkDaoConnection();
        User user = checkUser(userId);
        List<Order> orders = checkUserOrders(user);

        return calculateTotal(orders);
    }

    private void checkDaoConnection() {
        if (userDao == null) {
            throw new DatabaseConnectionException();
        }
    }

    private User checkUser(String userId) {
        User user = userDao.getUser(userId);
        if (user == null) {
            throw new InvalidUserException(INVALID_USER);
        }
        return user;
    }

    private List<Order> checkUserOrders(User user) {
        List<Order> orders = user.getAllOrders();
        if (orders.isEmpty()) {
            throw new InvalidUserException(EMPTY_ORDERS);
        }
        return orders;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private Double calculateTotal(List<Order> orders) {
        return orders.stream()
                .filter(Order::isSubmitted)
                .filter(this::throwExceptionIfOrderTotalIsLessThanZero)
                .mapToDouble(Order::total)
                .sum();
    }

    private boolean throwExceptionIfOrderTotalIsLessThanZero(Order order) {
        if (isLessZero(order)) {
            throw new InvalidUserException(ORDER_TOTAL_LESS_THAN_ZERO);
        }
        return true;
    }

    private boolean isLessZero(Order order) {
        return order.total() < MINIMUM_VALUE;
    }
}
