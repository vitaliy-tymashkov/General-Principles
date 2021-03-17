package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

import static com.epam.engx.cleancode.errorhandling.task1.ErrorStatus.*;

public class UserReportBuilder {

    private static final int MINIMUM_VALUE = 0;

    private UserDao userDao;

    public double getUserTotalOrderAmount(String userId) {
        validateDaoOnNull();
        User user = userDao.getUser(userId);
        validateUserOnNull(user);
        List<Order> orders = getUserTotalOrderAmount(user);

        return calculateTotal(orders);
    }

    private void validateDaoOnNull() {
        if (userDao == null) {
            throw new DatabaseConnectionException();
        }
    }

    private void validateUserOnNull(User user) {
        if (user == null) {
            throw new InvalidUserException(INVALID_USER);
        }
    }

    private List<Order> getUserTotalOrderAmount(User user) {
        List<Order> orders = user.getAllOrders();
        validateOrders(orders);
        return orders;
    }

    private double calculateTotal(List<Order> orders) {
        return orders.stream()
                .filter(Order::isSubmitted)
                .filter(this::isOrderTotalIsLessThanZero)
                .mapToDouble(Order::total)
                .sum();
    }

    private void validateOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new InvalidUserException(EMPTY_ORDERS);
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private boolean isOrderTotalIsLessThanZero(Order order) {
        validateTotalFromOrder(order);
        return true;
    }

    private void validateTotalFromOrder(Order order) {
        if (isLessZero(order)) {
            throw new InvalidUserException(ORDER_TOTAL_LESS_THAN_ZERO);
        }
    }

    private boolean isLessZero(Order order) {
        return order.total() < MINIMUM_VALUE;
    }
}
