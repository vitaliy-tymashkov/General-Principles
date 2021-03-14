package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

import static com.epam.engx.cleancode.errorhandling.task1.ERROR_ENUM.*;

public class UserReportBuilder {

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

    private Double calculateTotal(List<Order> orders) {
        Double sum = 0.0D;
        for (Order order : orders) {
            if (order.isSubmitted()) {
                Double total = order.total();
                if (total < 0) {
                    throw new InvalidUserException(ORDER_TOTAL_LESS_THAN_ZERO);
                }
                sum += total;
            }
        }
        return sum;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
