package service;

import dao.OrderDao;
import lombok.Data;
import model.order.Order;
import model.services.SubService;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderService {
    OrderDao orderDao;

    public void update(Order order) {
        orderDao.update(order);
    }

    public boolean saveOrder(Order order) {
        orderDao.create(order);
        return true;
    }

    public Order findById(int id) {
        return orderDao.findById(id);
    }

    public List<Order> findBySubService(SubService subService) {
        return orderDao.findBySubService(subService);
    }
}
