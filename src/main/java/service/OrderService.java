package service;

import dao.OrderDao;
import lombok.Data;
import model.order.Order;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderService {
    OrderDao orderDao;

    public void updateStatus(Order order) {
        orderDao.update(order);
    }
}
