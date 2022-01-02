package service;

import dao.OrderDao;
import model.order.Order;

/**
 * @author Negin Mousavi
 */
public class OrderService {
    OrderDao orderDao;

    public void updateStatus(Order order) {
        orderDao.update(order);
    }
}
